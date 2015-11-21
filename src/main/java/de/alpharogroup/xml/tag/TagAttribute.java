package de.alpharogroup.xml.tag;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagAttribute
{
	
	/** The name of the attribute. */
	private String name;
	
	/** The delimiter of the values from the attribute. */
	private String delimiter;

	/** The values of the attribute. */
	@Singular 
	private List<String> values;
	
	{
		onInitializerBlock();
	}
	
	public String joinValues() {
		return String.join(delimiter, values);
	}
	
	protected void onInitializerBlock(Object... objects) {
	}
}
