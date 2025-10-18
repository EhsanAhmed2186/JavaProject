package Library.model;

import Library.exception.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private ArrayList<Item> items;
    private ArrayList<Member> members;
    private ArrayList<CheckOutRecord> checkOutRecords;
    private Member loggedInMember;

    public Library() {
        items = new ArrayList<>();
        members = new ArrayList<>();
        checkOutRecords = new ArrayList<>();
        loggedInMember = null;
    }


    public void addItem(Item item) {
        items.add(item);
    }

    public String addBook(String title, String category, ArrayList<String> authors, LocalDate publishDate,
                          String publisherName, int numberOfPages, String isbn) {
        Book book = new Book(title, category, authors, publishDate, false, publisherName, numberOfPages, isbn);
        addItem(book);
        return book.getItemId();
    }

    public String addPublication(String title, String category, ArrayList<String> authors, LocalDate publishDate,
                                 String publisherName) {
        Publication pub = new Publication(title, category, authors, publishDate, false, publisherName);
        addItem(pub);
        return pub.getItemId();
    }


    public Item findItem(String itemId) throws InvalidItemException {
        for (Item it : items) {
            if (it.getItemId().equals(itemId)) return it;
        }
        throw new InvalidItemException(itemId);
    }

    public ArrayList<Item> findItems(String title, String author) {
        ArrayList<Item> res = new ArrayList<>();
        for (Item it : items) {
            if (it.getTitle().toLowerCase().contains(title.toLowerCase()) && it.isAnAuthor(author)) {
                res.add(it);
            }
        }
        return res.isEmpty() ? null : res;
    }

    public void addMember(String name, String id) {
        members.add(new Member(name, id));
    }

    public Member findMember(String id) {
        for (Member m : members) {
            if (m.getMemberId().equals(id)) {
                return m;
            }
        }
        return null;
    }

    public List<Member> getMembers() {
        return members;
    }



    public void loginMember(String memberId) throws InvalidMemberException {
        Member m = findMember(memberId);
        if (m == null) throw new InvalidMemberException(memberId);
        loggedInMember = m;
    }

    public void logoutMember() {
        loggedInMember = null;
    }

    public Member getLoggedInMember() {
        return loggedInMember;
    }

    public void checkOut(String itemId, String memberId) throws InvalidItemException, CheckedOutException {
        Item it = findItem(itemId);
        Member m = findMember(memberId);
        if (m == null) throw new CheckedOutException("Invalid member id or Item id");
        if (it.getIsCheckedOut()) throw new CheckedOutException(itemId, it.getTitle());

        it.setIsCheckedOut(true);
        CheckOutRecord rec = new CheckOutRecord(memberId, itemId);
        checkOutRecords.add(rec);
    }

    public void checkIn(String itemId) throws InvalidItemException, CheckedOutException {
        Item it = findItem(itemId);
        if (!it.getIsCheckedOut()) throw new CheckedOutException("Item is not checked out");

        CheckOutRecord rec = findActiveCheckOutRecord(itemId);
        if (rec == null) throw new CheckedOutException("No active checkout record found");

        it.setIsCheckedOut(false);
        rec.returnItem();
    }

    public void extendCheckOut(String itemId, String memberId) throws InvalidItemException, CheckedOutException {
        Item it = findItem(itemId);
        Member m = findMember(memberId);
        if (m == null) throw new CheckedOutException("Invalid member id or Item id");
        if (!it.getIsCheckedOut()) throw new CheckedOutException("Item is not checked out");

        CheckOutRecord rec = findCheckOutRecord(itemId, memberId);
        if (rec == null) throw new CheckedOutException("Invalid member id or Item id");

        if (!rec.extendOnce()) {
            throw new CheckedOutException("Already extended once. Cannot extend again");
        }
    }

    public void checkOut(String itemId) throws InvalidItemException, CheckedOutException {
        if (loggedInMember != null) checkOut(itemId, loggedInMember.getMemberId());
    }

    public void extendCheckOut(String itemId) throws InvalidItemException, CheckedOutException {
        if (loggedInMember != null) extendCheckOut(itemId, loggedInMember.getMemberId());
    }


    private CheckOutRecord findActiveCheckOutRecord(String itemId) {
        for (CheckOutRecord r : checkOutRecords) {
            if (r.getItemId().equals(itemId) && r.getCheckInTime() == null) return r;
        }
        return null;
    }

    private CheckOutRecord findCheckOutRecord(String itemId, String memberId) {
        for (CheckOutRecord r : checkOutRecords) {
            if (r.getItemId().equals(itemId) && r.getMemberId().equals(memberId) && r.getCheckInTime() == null) {
                return r;
            }
        }
        return null;
    }


    public ArrayList<CheckOutRecord> getCheckOutRecordsForMember(String memberId) {
        ArrayList<CheckOutRecord> res = new ArrayList<>();
        for (CheckOutRecord r : checkOutRecords) {
            if (r.getMemberId().equals(memberId)) res.add(r);
        }
        return res.isEmpty() ? null : res;
    }


    public ArrayList<Item> getAllItems() { return items; }
    public ArrayList<Member> getAllMembers() { return members; }
    public ArrayList<CheckOutRecord> getAllCheckOutRecords() { return checkOutRecords; }
}
