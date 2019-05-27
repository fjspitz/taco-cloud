package tacos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;

@Entity
@Table(name="Taco_Order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Delivery name is required")
	private String deliveryName;
	@NotBlank(message = "Street is required")
	private String deliveryStreet;
	@NotBlank(message="City is required")
	private String deliveryCity;
	@Size(min = 2, max = 2)
	@NotBlank(message="State is required")
	private String deliveryState;
	@NotBlank(message="Zip code is required")
	private String deliveryZip;
	@CreditCardNumber(message="Not a valid credit card number")
	private String ccNumber;
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="Must be formatted MM/YY")
	private String ccExpiration;
	@Digits(integer=3, fraction=0, message="Invalid CVV")
	private String ccCVV;
	private Date placedAt;
	@ManyToMany(targetEntity = Taco.class)
	private List<Taco> tacos = new ArrayList<>();
	
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
	
	public Order() {
		this.placedAt = new Date();
	}
	
	public Order(String deliveryName, String street, String city, String state, String zip, String ccNumber,
			String ccExpiration, String ccCVV) {
		super();
		this.deliveryName = deliveryName;
		this.deliveryStreet = street;
		this.deliveryCity = city;
		this.deliveryState = state;
		this.deliveryZip = zip;
		this.ccNumber = ccNumber;
		this.ccExpiration = ccExpiration;
		this.ccCVV = ccCVV;
		this.placedAt = new Date();
	}
	
	public void addDesign(Taco design) {
	    this.tacos.add(design);
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public void setDeliveryStreet(String deliveryStreet) {
		this.deliveryStreet = deliveryStreet;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	public String getDeliveryState() {
		return deliveryState;
	}

	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}

	public String getDeliveryZip() {
		return deliveryZip;
	}

	public void setDeliveryZip(String deliveryZip) {
		this.deliveryZip = deliveryZip;
	}

	public String getCcNumber() {
		return ccNumber;
	}

	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	public String getCcExpiration() {
		return ccExpiration;
	}

	public void setCcExpiration(String ccExpiration) {
		this.ccExpiration = ccExpiration;
	}

	public String getCcCVV() {
		return ccCVV;
	}

	public void setCcCVV(String ccCVV) {
		this.ccCVV = ccCVV;
	}

	@Override
	public String toString() {
		return "Order [deliveryName=" + deliveryName + ", street=" + deliveryStreet + ", city=" + deliveryCity + ", state=" + deliveryState + ", zip=" + deliveryZip
				+ ", ccNumber=" + ccNumber + ", ccExpiration=" + ccExpiration + ", date=" + placedAt + "]";
	}

	public Date getPlacedAt() {
		return placedAt;
	}

	public void setPlacedAt(Date date) {
		this.placedAt = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Taco> getTacos() {
		return tacos;
	}

	public void setTacos(List<Taco> tacos) {
		this.tacos = tacos;
	}
	
}
