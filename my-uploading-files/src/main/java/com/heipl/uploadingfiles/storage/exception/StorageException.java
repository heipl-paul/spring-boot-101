package com.heipl.uploadingfiles.storage.exception;

/**
 * WHAT IS EVEN THE POINT OF THIS CLASS?
 * TELL ME TUTORIAL MAN, TELL ME!
 */
public class StorageException extends RuntimeException {

	public StorageException(final String message) {
		super(message);
	}

	public StorageException(final String message, final Throwable cause) {
		super(message, cause);
	}
}