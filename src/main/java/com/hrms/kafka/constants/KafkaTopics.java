package com.hrms.kafka.constants;

public enum KafkaTopics {

	PAYROLL_PAYPROCESS("payroll-payprocess"),
	PAYROLL_GENERATED("payroll-generated"), PAYROLL_APPROVED("payroll-approved"),
	PAYROLL_DISBURSED("payroll-disbursed"),

	ATTENDANCE_MARKED("attendance-marked"), ATTENDANCE_PROCESSED("attendance-processed"),
	ATTENDANCE_CORRECTED("attendance-corrected"),

	LEAVE_REQUESTED("leave-requested"), LEAVE_APPROVED("leave-approved"), LEAVE_REJECTED("leave-rejected"),
	LEAVE_CANCELLED("leave-cancelled"),

	EMPLOYEE_ONBOARDED("employee-onboarded"), EMPLOYEE_PROMOTED("employee-promoted"),
	EMPLOYEE_TERMINATED("employee-terminated"),

	CANDIDATE_APPLIED("candidate-applied"), CANDIDATE_SHORTLISTED("candidate-shortlisted"),
	CANDIDATE_HIRED("candidate-hired"), CANDIDATE_REJECTED("candidate-rejected"),

	PERFORMANCE_REVIEWED("performance-reviewed"), PERFORMANCE_FEEDBACK_GIVEN("performance-feedback-given"),

	EMAIL_SENT("email-sent"), SMS_SENT("sms-sent"), PUSH_NOTIFICATION_SENT("push-notification-sent"),

	WORKFLOW_INITIATED("workflow-initiated"), WORKFLOW_APPROVED("workflow-approved"),
	WORKFLOW_REJECTED("workflow-rejected"),

	DOCUMENT_UPLOADED("document-uploaded"), DOCUMENT_VERIFIED("document-verified"),
	DOCUMENT_EXPIRED("document-expired");

	private final String topicName;

	KafkaTopics(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicName() {
		return topicName;
	}
}
