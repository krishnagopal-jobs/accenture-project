package com.kg;

import java.io.Serializable;
import java.util.Objects;

import javax.json.bind.annotation.JsonbNillable;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.config.PropertyOrderStrategy;

import lombok.Data;

@Data
@JsonbNillable
@JsonbPropertyOrder(PropertyOrderStrategy.REVERSE)
public class SampleObject implements Serializable, Comparable<SampleObject> {

	private static final long serialVersionUID = 1L;

	private Integer uuid;
	
	private String name;
	
	private String role;
	
	public Integer getUuid() {
		return uuid;
	}

	public void setUuid(Integer uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

	@Override
	public int compareTo(SampleObject o) {
		Objects.requireNonNull(o);
		if (Objects.nonNull(uuid) || Objects.nonNull(o.uuid)) {
			if (Objects.isNull(uuid)) {
				return -1;
			}
			if (Objects.isNull(o.uuid)) {
				return 1;
			}
			return uuid.compareTo(o.uuid);
		}

		if (Objects.nonNull(name) || Objects.nonNull(o.name)) {
			if (Objects.isNull(name)) {
				return -1;
			}
			if (Objects.isNull(o.name)) {
				return 1;
			}
			return name.compareTo(o.name);
		}

		if (Objects.nonNull(role) || Objects.nonNull(o.role)) {
			if (Objects.isNull(role)) {
				return -1;
			}
			if (Objects.isNull(o.role)) {
				return 1;
			}
			return role.compareTo(o.role);
		}
		return 0;
	}

}