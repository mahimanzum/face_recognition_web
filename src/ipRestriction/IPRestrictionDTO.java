package ipRestriction;

import annotation.ColumnName;
import annotation.CurrentTime;
import annotation.PrimaryKey;
import annotation.TableName;

@TableName("at_ip_restriction")
public class IPRestrictionDTO {
	@PrimaryKey
	@ColumnName("ID")
	private long ID;
	
	@ColumnName("IP")
	private String IP;
	
	@ColumnName("lastHitTime")
	private long lastHitTime;
	
	@ColumnName("nextHitTimeAfter")
	private long nextHitTimeAfter;
	
	@ColumnName("type")
	private int type;
	
	@ColumnName("hitCount")
	private int hitCount = 1;
	
	@ColumnName("username")
	private String username;
	
	@CurrentTime
	@ColumnName("lastModificationTime")
	private long lastModificationTime;
	
	@ColumnName("isDeleted")
	private boolean isDeleted;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public long getLastHitTime() {
		return lastHitTime;
	}

	public void setLastHitTime(long lastHitTime) {
		this.lastHitTime = lastHitTime;
	}

	public long getNextHitTimeAfter() {
		return nextHitTimeAfter;
	}

	public void setNextHitTimeAfter(long nextHitTimeAfter) {
		this.nextHitTimeAfter = nextHitTimeAfter;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getLastModificationTime() {
		return lastModificationTime;
	}

	public void setLastModificationTime(long lastModificationTime) {
		this.lastModificationTime = lastModificationTime;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "IPRestrictionDTO [ID=" + ID + ", IP=" + IP + ", lastHitTime=" + lastHitTime + ", nextHitTimeAfter="
				+ nextHitTimeAfter + ", type=" + type + ", username=" + username + ", lastModificationTime="
				+ lastModificationTime + ", isDeleted=" + isDeleted + "]";
	}

	
	
	
}
