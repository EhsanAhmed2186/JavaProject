package Library.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CheckOutRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    private String memberId;
    private String itemId;
    private LocalDateTime checkOutTime;
    private LocalDateTime expectedCheckInTime;
    private LocalDateTime checkInTime;
    private boolean extendedOnce = false;

    public CheckOutRecord(String memberId, String itemId) {
        this.memberId = memberId;
        this.itemId = itemId;
        this.checkOutTime = LocalDateTime.now();
        this.expectedCheckInTime = this.checkOutTime.plusWeeks(1);
        this.checkInTime = null;
    }

    public String getMemberId() { return memberId; }
    public String getItemId() { return itemId; }
    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public LocalDateTime getExpectedCheckInTime() { return expectedCheckInTime; }
    public LocalDateTime getCheckInTime() { return checkInTime; }
    public boolean isExtendedOnce() { return extendedOnce; }

    public void updateExpectedCheckinTime(LocalDateTime t) {
        this.expectedCheckInTime = t;
    }

    public void returnItem() {
        this.checkInTime = LocalDateTime.now();
    }

    public boolean extendOnce() {
        if (extendedOnce) return false;
        LocalDateTime initialExpected = this.checkOutTime.plusWeeks(1);
        if (!this.expectedCheckInTime.isAfter(initialExpected)) {
            this.expectedCheckInTime = this.expectedCheckInTime.plusWeeks(1);
            this.extendedOnce = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String outCheckout = checkOutTime != null ? checkOutTime.format(formatter) : "null";
        String outCheckin = checkInTime != null ? checkInTime.format(formatter) : "null";
        return itemId + "\t" + memberId + "\t" + outCheckout + "\t" + outCheckin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CheckOutRecord other = (CheckOutRecord) obj;
        return Objects.equals(memberId, other.memberId)
                && Objects.equals(itemId, other.itemId)
                && Objects.equals(checkOutTime, other.checkOutTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId, itemId, checkOutTime);
    }
}
