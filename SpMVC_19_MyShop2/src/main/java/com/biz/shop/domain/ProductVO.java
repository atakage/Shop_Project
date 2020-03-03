 package com.biz.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * @Email : email 형식
 * @NotBlank, @NotNull, @NotEmpty : 공백형식
 * @Null : null일 경우만 통과
 * @Size(max, min)
 * @Max()
 * @Min()
 * @DecimalMax(x) : x값 이하의 실수
 * @DecimalMin(x) : x값 이상의 실수
 * @Digits(정수) : 정수 자릿수 검사
 * @Digits(숫자, fraction=y) : 숫자 자릿수 이하이면서
 * 								소수점  y 자릿수 이하
 * @Pattern(regexp = "//d{1,15}") 1부터 15자리 까지의 숫자만 가능 
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Entity
@Table(name = "tbl_product", schema = "emsDB")
public class ProductVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_id")
	private long id;

	// 입력값이 공백일 경우 error
	@NotBlank(message = "* 상품코드는 공백이 되면 안됩니다.")
	@NotEmpty // NotBlank와 비슷한 기능
	@NotNull // NotBlank와 비슷한 기능

	// 문자열의 길이가 맞지 않을경우
	// Size(min, max)
	// min(), max()
	@Size(max = 13, message = " * 상품코드는 13자리 이하만 가능합니다.")
	// jsp의 path가 name과 같다.
	@Column(name = "p_code", length = 13, unique = true, nullable = false)
	private String p_code;

	// @phoneNumber() : 전화번호 형식 062-111-1234
	
	/*
	 * // id 체크 정규식 : 숫자, 영문만 입력 가능 
	 * var regExpId = /^[0-9a-z]+$/;
	 * 
	 * 비밀번호 규칙 정규식 
	 * : 숫자, 특문 각 1회 이상, 영문은 2개 이상 사용하여 8자리 이상 입력 
	 * var regExpPw = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/;
	 * 
	 * // 이메일주소 형식 체크 정규식 
	 * var regExpEm =/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z] {2,3}$/i;
	 * 
	 */

	// @Email()
	// 문자열이 이메일 형식이 아닐 경우 error
	@NotBlank(message = "* 상품이름은 공백이 될 수 없습니다.")
	@Column(name = "p_name")
	private String p_name;

	@Size(min = 5, max = 5, message = "* 품목코드를 확인하세요.")
	@Column(name = "p_bcode", length = 5)
	private String p_bcode;
	
	@Size(min = 5, max = 5 , message = "* 거래처코드를 확인하세요.")
	@Column(name = "p_dcode", length = 5)
	private String p_dcode;

	@Column(name = "p_iprice")
	private int p_iprice;

	@Column(name = "p_oprice")
	private int p_oprice;

	@Column(name = "p_detail")
	@Type(type = "text")
	private String p_detail;
}
