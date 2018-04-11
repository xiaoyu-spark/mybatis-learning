package training.utils;

import java.io.*;

public class ProcessUtils {

    public static int exec(String cmd) throws IOException {
        return exec(cmd, System.out, System.err);
    }

    public static int exec(String command, OutputStream out) throws IOException {
        return exec(command, out, System.err);
    }

    public static int exec(String command, OutputStream out, OutputStream err) throws IOException {
        Process process = Runtime.getRuntime().exec(command);
        return executeCommand(process, out, err);
    }

    public static int executeCommandLine(String command) throws IOException {
        return executeCommandLine(command, System.out, System.err);
    }

    public static int executeCommandLine(String command, File workdir) throws IOException {
        return executeCommandLine(command, workdir, System.out, System.err);
    }

    public static int executeCommandLine(String command, OutputStream out, OutputStream err) throws IOException {
        return executeCommandLine(command, null, out, err);
    }

    public static int executeCommandLine(String command, File workdir, OutputStream out, OutputStream err) throws IOException {
        String osName = System.getProperty("os.name");
        String[] commands;
        if (osName.startsWith("Windows")) {
            commands = new String[] {"cmd.exe", "/C", command};
        } else {
            commands = new String[] {"/bin/sh", "-c", command};
        }
        ProcessBuilder builder = new ProcessBuilder(commands);
        if (workdir != null) {
            builder = builder.directory(workdir);
        }
        Process process = builder.start();
        return executeCommand(process, out, err);
    }

    public static void executeGuard(String command) throws IOException {
        executeCommandLine("sh -c \"nohup " + command + " > /dev/null 2>&1 &\"");
    }

    private static int executeCommand(Process process, OutputStream out, OutputStream err) throws IOException {
        StreamPumper outputPumper = new StreamPumper(process.getInputStream(), out);
        StreamPumper errorPumper = new StreamPumper(process.getErrorStream(), err);
        outputPumper.start();
        errorPumper.start();
        try {
            int returnValue = process.waitFor();
            synchronized (outputPumper) {
                if (!outputPumper.isDone()) {
                    outputPumper.wait();
                }
            }
            synchronized (errorPumper) {
                if (!errorPumper.isDone()) {
                    errorPumper.wait();
                }
            }
            return returnValue;
        } catch (InterruptedException ex) {
            throw new IOException("Error while executing external command, process killed.");
        } finally {
            outputPumper.close();
            errorPumper.close();
            process.destroy();
        }
    }

    private static class StreamPumper extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private boolean done;

        public StreamPumper(InputStream in, OutputStream out) {
            this.in = new BufferedReader(new InputStreamReader(in));
            if (out != null) {
                this.out = new PrintWriter(out);
            }
        }

        public void run() {
            try {
                String s = in.readLine();
                while (s != null) {
                    if (out != null) {
                        out.println(s);
                        out.flush();
                    }
                    s = in.readLine();
                }
            }
            catch (Throwable e) {
                e.printStackTrace();
                // Catched everything so the streams will be closed and flagged as done.
            } finally {
                try {
                    if (in != null) {
                        in.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                done = true;
                synchronized (this) {
                    this.notifyAll();
                }
            }
        }

        public void close() {
            if (out != null) {
                out.flush();
            }
        }

        public boolean isDone() {
            return done;
        }
    }

}
