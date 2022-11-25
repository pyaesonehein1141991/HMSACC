package org.tech.hms.common.dto.brachDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {
	private String id;
	private String name;
	private String code;
	private String description;

}
