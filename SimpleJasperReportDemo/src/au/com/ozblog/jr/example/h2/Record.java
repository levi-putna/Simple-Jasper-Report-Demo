package au.com.ozblog.jr.example.h2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "record")
public class Record {
	private int id;
	private String name;
	private int value;

	public Record() {
		// this form used by Hibernate
	}

	public Record(int id, String name, int value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}

	@Id
	public int getId() {
		return id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@Column(name = "value")
	public int getValue() {
		return value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
