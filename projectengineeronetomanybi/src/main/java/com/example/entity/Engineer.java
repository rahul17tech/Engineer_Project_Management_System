package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Engineer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eid;

	private String engineerName;
	private String role;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "proj_id", referencedColumnName = "pid")
	@JsonIgnore
	private Project project;

	@Override
	public String toString() {
		return "Engineer [eid=" + eid + ", engineerName=" + engineerName + "]";
	}

}
