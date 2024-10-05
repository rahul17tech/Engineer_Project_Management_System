package com.example.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorMessage {
	private int statuscode;
	private Date timestamp;
	private String message;
	private String description;
}
