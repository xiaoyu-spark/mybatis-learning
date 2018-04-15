package training.bean;

import java.util.*;

public class Role implements Persistable {
    private String id;
    private String name;
    private String enName;
    private boolean enabled = true;
    private List<String> permissions;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getPermissions() {
        if (permissions == null) {
            return Collections.emptyList();
        }
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public boolean hasPermission(String permission) {
        return permissions != null && permissions.contains(permission);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public boolean isNew() {
        return id == null;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Role)) {
            return false;
        }
        Role that = (Role) o;
        return this.id.equals(that.id);
    }

    @Override
    public String toString() {
        return id + "/" + name + "/" + enName + "/" + createdAt;
    }
}
