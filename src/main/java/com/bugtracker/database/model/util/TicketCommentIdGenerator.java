package com.bugtracker.database.model.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bugtracker.database.model.TicketComment;

public class TicketCommentIdGenerator implements IdentifierGenerator {

	/** The used logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TicketCommentIdGenerator.class);

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		TicketComment ticketComment = (TicketComment) object;

		return getNextId(connection, ticketComment);
	}

	private synchronized Integer getNextId(Connection connection, TicketComment ticketComment) {
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select count(" + TicketComment.TICKET_COMMENT_ID_COLUMN_NAME
					+ ") from " + Tables.TICKET_COMMENTS + " where " + TicketComment.TICKET_COLUMN_NAME + "="
					+ ticketComment.getTicket().getTicketId());

			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			LOGGER.error("Failed to generate a suitable id", e);
		}

		return null;
	}
}
