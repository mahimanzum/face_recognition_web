package pb_notifications;
import java.util.*; 


public class Pb_notificationsDTO {

	public long iD = 0;
	public boolean isSeen = false;
	public boolean isHidden = false;
	public int source = 0;
	public int destination = 0;
	public long fromId = 0;
	public long toId = 0;
    public String text = "";
    public String url = "";
	public long entryDate = 0;
	public long seenDate = 0;
	public long showingDate = 0;
	public boolean sendAlarm = true;
	public boolean sendSms = false;
	public boolean sendMail = false;
	public boolean sendPush = false;
	public boolean mailSent = false;
	public boolean smsSent = false;
	public boolean pushSent = false;
	public boolean isDeleted = false;
	
    @Override
	public String toString() {
            return "$Pb_notificationsDTO[" +
            " iD = " + iD +
            " isSeen = " + isSeen +
            " isHidden = " + isHidden +
            " source = " + source +
            " destination = " + destination +
            " fromId = " + fromId +
            " toId = " + toId +
            " text = " + text +
            " url = " + url +
            " entryDate = " + entryDate +
            " seenDate = " + seenDate +
            " showingDate = " + showingDate +
            " sendAlarm = " + sendAlarm +
            " sendSms = " + sendSms +
            " sendMail = " + sendMail +
            " sendPush = " + sendPush +
            " mailSent = " + mailSent +
            " smsSent = " + smsSent +
            " pushSent = " + pushSent +
            " isDeleted = " + isDeleted +
            "]";
    }

}