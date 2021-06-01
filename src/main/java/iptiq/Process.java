package iptiq;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public final class Process implements Comparable<Process> {

    public final static int RUNNING = 0;
    public final static int TERMINATED = 1;

    private int status;
    private UUID PID;
    private Priority priority;
    private LocalDateTime timestamp;


    public Process(Priority priority) {
        this.status = RUNNING;
        this.priority = priority;
        this.PID = UUID.randomUUID();
        this.timestamp = LocalDateTime.now();
    }

    protected void kill() {
        this.status = TERMINATED;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public UUID getPID() {
        return PID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Process other) {
        return timestamp.compareTo(other.timestamp);
    }

    @Override
    public String toString() {
        return "Process{" +
                "status=" + status +
                ", PID=" + PID +
                ", priority=" + priority +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Process process = (Process) o;
        return status == process.status &&
                Objects.equals(PID, process.PID) &&
                priority == process.priority &&
                Objects.equals(timestamp, process.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, PID, priority, timestamp);
    }
}
