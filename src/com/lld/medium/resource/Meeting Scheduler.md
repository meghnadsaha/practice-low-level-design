---

## Low-Level Design: Meeting Scheduler

### Problem Statement
You are tasked with designing a meeting scheduler for a building with `N` rooms. The system should handle streams of meeting time requests and schedule them appropriately. Additionally, the system must:

1. Store audit logs for each room after scheduling meetings.
2. Delete audit logs after `x` days.
3. Ensure that room capacity constraints are respected when scheduling meetings.
4. Minimize the spillage of free time when scheduling meetings.

### Requirements

1. **Meeting Scheduling**:
    - Schedule meetings based on the streams of meeting time requests.
    - Ensure that the rooms are allocated efficiently to minimize free time spillage.

2. **Audit Logs**:
    - Store audit logs for each room after scheduling meetings.
    - Delete audit logs after `x` days.

3. **Capacity Constraints**:
    - Each room has a capacity limit. Ensure that meetings are only scheduled in rooms that can accommodate the number of attendees.

4. **Minimizing Spillage**:
    - Spillage Example: If Room 1 is free from 9-10 AM and Room 2 is free from 9 AM - 12 PM, and a meeting request comes for 9-10 AM, it should be scheduled in Room 1 to keep Room 2 available for a potentially longer meeting.

5. **Concurrency Handling**:
    - The system should handle concurrent meeting requests and ensure that meetings are scheduled without conflicts.

### Follow-up Questions

1. How would you design the system to handle the audit logs?
2. How would you implement the deletion of audit logs after `x` days?
3. What strategies would you use to respect room capacity constraints?
4. How would you minimize spillage when scheduling meetings?
5. How would you handle concurrency in the system to ensure meetings are scheduled correctly without conflicts?

---

### Java Implementation Criteria for Meeting Scheduler

#### 1. **Class Design**

- **Room Class**:
   - Attributes: `id`, `capacity`, `availability` (list of time slots), `auditLogs` (list of audit logs).
   - Methods: `addMeeting(Meeting meeting)`, `removeMeeting(Meeting meeting)`, `addAuditLog(AuditLog log)`, `removeOldAuditLogs(LocalDate cutoffDate)`.

- **Meeting Class**:
   - Attributes: `id`, `startTime`, `endTime`, `attendees`.
   - Methods: `getDuration()`, `conflictsWith(Meeting other)`.

- **AuditLog Class**:
   - Attributes: `id`, `roomId`, `meetingId`, `timestamp`.
   - Methods: `isOlderThan(LocalDate cutoffDate)`.

- **Scheduler Class**:
   - Attributes: `rooms` (list of Room objects), `auditLogRetentionDays`.
   - Methods: `scheduleMeeting(Meeting meeting)`, `findAvailableRoom(Meeting meeting)`, `logAudit(Meeting meeting, Room room)`, `cleanupAuditLogs()`.

#### 2. **Meeting Scheduling**

- **Scheduling Logic**:
   - Iterate over the list of rooms to find an available room for the meeting.
   - Ensure the room’s capacity can accommodate the meeting’s attendees.
   - Minimize free time spillage by preferring rooms with time slots that best match the meeting duration.

#### 3. **Audit Logs**

- **Storing Audit Logs**:
   - After successfully scheduling a meeting, create an audit log entry and add it to the room’s audit logs.
   - Include details such as the meeting ID, room ID, and timestamp.

- **Deleting Audit Logs**:
   - Implement a method to periodically clean up old audit logs.
   - Compare the current date with the log’s timestamp and delete logs older than the specified retention period.

#### 4. **Capacity Constraints**

- **Capacity Check**:
   - Before scheduling a meeting, ensure the chosen room’s capacity is sufficient for the number of attendees.
   - Reject the meeting request if no room can accommodate the required capacity.

#### 5. **Minimizing Spillage**

- **Spillage Reduction**:
   - Sort rooms by their availability and find the best match for the meeting duration.
   - Prefer rooms where the time slot matches the meeting duration to avoid leaving small unusable time slots.

#### 6. **Concurrency Handling**

- **Synchronization**:
   - Use synchronized methods or blocks to handle concurrent access to shared resources such as room availability and audit logs.
   - Ensure atomic operations when scheduling meetings to prevent conflicts and double booking.

#### 7. **Additional Considerations**

- **Exception Handling**:
   - Implement robust error handling for scenarios such as no available room, exceeding capacity, and conflicting meeting times.

- **Testing**:
   - Write unit tests to verify the functionality of each component, including scheduling logic, capacity checks, spillage minimization, and audit log management.
   - Perform concurrency tests to ensure the scheduler handles simultaneous meeting requests correctly.

- **Scalability**:
   - Consider using data structures that support efficient searching and updating of available time slots, such as interval trees or priority queues.

- **Extensibility**:
   - Design the system to allow easy addition of new features, such as different types of rooms, recurring meetings, or advanced search criteria for meeting rooms.

### Activity Diagram
<img src=""/>

### Class Diagram
<img src=""/>
