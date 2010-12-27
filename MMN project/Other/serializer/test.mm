<matamodel name="mm">
	<shapeCategory id="default">
		<shape id="actor">
			<attribute type="shapeLabel">
				<value type="label">actor</value>
			</attribute>
			<attribute type="shapeImage">
				<value type="image">org/cvut/MMN/actor.jpg</value>
			</attribute>
		</shape>
		<shape id="uce_case">
			<attribute type="shapeImage">
				<value type="image">org/cvut/MMN/oval.jpg</value>
			</attribute>
		</shape>
	</shapeCategory>
	<shapeCategory id="custom">
		.
		.
		.
	</shapeCategory>



	<connectionCategory id="default">
		<connection id="association">
			<attribute type="lineType">
				<value type="line">simple</value>
				<value type="width">1</value>
			</attribute>
			<rules>
				<rule>
					<source>actor</source>
					<target>uce_case</source>
					<sourceMultiplicity>*</sourceMultiplicity>
					<targetMultiplicity>*</targetMultiplicity>
				</rule
			</rules>
		</connection>
	</connectionCategory>

	<connectionCategory id="custom">
		.
		.
		.
	</connectionCategory>
</metamodel>

