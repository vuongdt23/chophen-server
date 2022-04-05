package com.uit.chophen.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the conversations database table.
 * 
 */
@Entity
@Table(name="conversations")
@NamedQuery(name="Conversation.findAll", query="SELECT c FROM Conversation c")
public class Conversation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="conversation_id")
	private int conversationId;

	@Column(name="conversation_timestamp")
	private String conversationTimestamp;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="conversation_user_one")
	private UserProfile userProfile1;

	//bi-directional many-to-one association to UserProfile
	@ManyToOne
	@JoinColumn(name="conversation_user_two")
	private UserProfile userProfile2;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="conversation")
	private List<Message> messages;

	public Conversation() {
	}

	public int getConversationId() {
		return this.conversationId;
	}

	public void setConversationId(int conversationId) {
		this.conversationId = conversationId;
	}

	public String getConversationTimestamp() {
		return this.conversationTimestamp;
	}

	public void setConversationTimestamp(String conversationTimestamp) {
		this.conversationTimestamp = conversationTimestamp;
	}

	public UserProfile getUserProfile1() {
		return this.userProfile1;
	}

	public void setUserProfile1(UserProfile userProfile1) {
		this.userProfile1 = userProfile1;
	}

	public UserProfile getUserProfile2() {
		return this.userProfile2;
	}

	public void setUserProfile2(UserProfile userProfile2) {
		this.userProfile2 = userProfile2;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setConversation(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setConversation(null);

		return message;
	}

}