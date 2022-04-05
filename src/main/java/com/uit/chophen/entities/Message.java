package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="message_id")
	private int messageId;

	@Column(name="message_content")
	private String messageContent;

	@Column(name="message_timestamp")
	private String messageTimestamp;

	//bi-directional many-to-one association to Conversation
	@ManyToOne
	@JoinColumn(name="message_conversation")
	private Conversation conversation;

	public Message() {
	}

	public int getMessageId() {
		return this.messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageContent() {
		return this.messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getMessageTimestamp() {
		return this.messageTimestamp;
	}

	public void setMessageTimestamp(String messageTimestamp) {
		this.messageTimestamp = messageTimestamp;
	}

	public Conversation getConversation() {
		return this.conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

}