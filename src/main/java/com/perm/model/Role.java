package com.perm.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/12/22.
 */
public class Role implements Serializable {

    private Integer id;

    private String roleCode;

    private String roleName;
    
    private List<Permission> permsList;
    
    private Integer[] permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

	public List<Permission> getPermsList() {
		return permsList;
	}

	public void setPermsList(List<Permission> permsList) {
		this.permsList = permsList;
	}

	
	
	public Integer[] getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer[] permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", permsList=" + permsList
				+ "]";
	}


    
    




	
}
