package com.hrms.kafka.constants;

public enum KafkaConsumerGroups {
    PAYROLL_GROUP("payroll-group"),
    ATTENDANCE_GROUP("attendance-group"),
    LEAVE_GROUP("leave-group"),
    EMPLOYEE_LIFE_CYCLE_GROUP("employee-life-cycle-group"),
    RECRUITMENT_GROUP("recruitment-group"),
    PERFORMANCE_GROUP("performance-group"),
    NOTIFICATION_GROUP("notification-group"),
    WORKFLOW_GROUP("workflow-group"),
    DOCUMENT_GROUP("document-group");

    private final String groupId;

    KafkaConsumerGroups(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
