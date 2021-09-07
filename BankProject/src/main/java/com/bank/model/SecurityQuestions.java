package com.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="securityquestions")
public class SecurityQuestions {
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String ans1;
	
	@Column(nullable = false)
	private String ans2;
	
	@Column(nullable = false)
	private String ans3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAns1() {
		return ans1;
	}

	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}

	public String getAns2() {
		return ans2;
	}

	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}

	public String getAns3() {
		return ans3;
	}

	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	
}
