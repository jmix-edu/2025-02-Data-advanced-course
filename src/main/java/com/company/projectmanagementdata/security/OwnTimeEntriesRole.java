package com.company.projectmanagementdata.security;

import com.company.projectmanagementdata.entity.TimeEntry;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "OwnTimeEntries", code = OwnTimeEntriesRole.CODE)
public interface OwnTimeEntriesRole {
    String CODE = "own-time-entries";

    @JpqlRowLevelPolicy(entityClass = TimeEntry.class, where = "{E}.user.id = :current_user_id")
    void timeEntry();
}