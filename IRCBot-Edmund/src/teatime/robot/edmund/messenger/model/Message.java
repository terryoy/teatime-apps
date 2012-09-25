package teatime.robot.edmund.messenger.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "edmund_messenger_message")
public class Message {

	@DatabaseField(generatedId=true)
	private Long id;
	@DatabaseField
	private boolean isPublic;
	@DatabaseField
	private String channel;
	@DatabaseField
	private String person;
	@DatabaseField
	private String message;
	@DatabaseField
	private Date createTimestamp;
	@DatabaseField
	private Date readTimestamp;
	
	public Date getCreateTimestamp() {
		return createTimestamp;
	}
	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	public Long getId() {
		return id;
	}
	protected void setId(Long id) {
		this.id = id;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getReadTimestamp() {
		return readTimestamp;
	}
	public void setReadTimestamp(Date readTimestamp) {
		this.readTimestamp = readTimestamp;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("{id:" + id);
		sb.append(", isPublic:" + isPublic);
		sb.append(", channel:" + channel);
		sb.append(", person:" + person);
		sb.append(", message:" + message);
		sb.append(", created:" + createTimestamp);
		sb.append(", readTimestamp:" + readTimestamp);
		sb.append("}");
		
		return sb.toString();
	}
	
}
