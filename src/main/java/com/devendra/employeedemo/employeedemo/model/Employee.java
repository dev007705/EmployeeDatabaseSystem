package com.devendra.employeedemo.employeedemo.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="employees")
public class Employee {

	@Id
	private String id;
	@NotNull(message = "Name may not be null")
	private String name;
	@NotNull(message = "DOJ may not be null")
	private String doj;
	private List<String> skill;
	@NotNull(message = "Designation may not be null")
	private String designation;
}
