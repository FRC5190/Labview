<?xml version='1.0' encoding='UTF-8'?>
<Project Type="Project" LVVersion="14008000">
	<Item Name="My Computer" Type="My Computer">
		<Property Name="server.app.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="server.control.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="server.tcp.enabled" Type="Bool">false</Property>
		<Property Name="server.tcp.port" Type="Int">0</Property>
		<Property Name="server.tcp.serviceName" Type="Str">My Computer/VI Server</Property>
		<Property Name="server.tcp.serviceName.default" Type="Str">My Computer/VI Server</Property>
		<Property Name="server.vi.callsEnabled" Type="Bool">true</Property>
		<Property Name="server.vi.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="specify.custom.address" Type="Bool">false</Property>
		<Item Name="Sensor Protocol" Type="Folder">
			<Item Name="CommandData Packet" Type="Folder"/>
			<Item Name="Global Data" Type="Folder">
				<Item Name="GetSensorByID.vi" Type="VI" URL="../GetSensorByID.vi"/>
				<Item Name="Global Data.vi" Type="VI" URL="../Global Data.vi"/>
				<Item Name="SetSensorData.vi" Type="VI" URL="../SetSensorData.vi"/>
			</Item>
			<Item Name="KeepState Packet" Type="Folder">
				<Item Name="KeepStatePacketParser.vi" Type="VI" URL="../../KeepStatePacketParser.vi"/>
			</Item>
			<Item Name="Sensor Data Packet" Type="Folder">
				<Item Name="Distance" Type="Folder">
					<Item Name="distanceSensorPacket.ctl" Type="VI" URL="../distanceSensorPacket.ctl"/>
					<Item Name="distanceSensorPacketParser.vi" Type="VI" URL="../distanceSensorPacketParser.vi"/>
				</Item>
				<Item Name="Encoder" Type="Folder">
					<Item Name="EncoderPacket.ctl" Type="VI" URL="../EncoderPacket.ctl"/>
					<Item Name="EncoderPacketParser.vi" Type="VI" URL="../EncoderPacketParser.vi"/>
				</Item>
				<Item Name="NavX" Type="Folder">
					<Item Name="navXPacket.ctl" Type="VI" URL="../navXPacket.ctl"/>
					<Item Name="navXPacketParser.vi" Type="VI" URL="../navXPacketParser.vi"/>
				</Item>
				<Item Name="Potentiometer" Type="Folder">
					<Item Name="PotentiometerPacket.ctl" Type="VI" URL="../PotentiometerPacket.ctl"/>
					<Item Name="PotentiometerPacketParser.vi" Type="VI" URL="../PotentiometerPacketParser.vi"/>
				</Item>
				<Item Name="Switch" Type="Folder">
					<Item Name="switchPacket.ctl" Type="VI" URL="../switchPacket.ctl"/>
					<Item Name="SwitchPacketParser.vi" Type="VI" URL="../SwitchPacketParser.vi"/>
				</Item>
				<Item Name="SensorData.ctl" Type="VI" URL="../SensorData.ctl"/>
				<Item Name="SensorDataPacketParser.vi" Type="VI" URL="../SensorDataPacketParser.vi"/>
				<Item Name="SensorStatus.ctl" Type="VI" URL="../SensorStatus.ctl"/>
				<Item Name="SensorType.ctl" Type="VI" URL="../SensorType.ctl"/>
			</Item>
			<Item Name="Utils" Type="Folder">
				<Item Name="16BitsTo4ByteFloat.vi" Type="VI" URL="../16BitsTo4ByteFloat.vi"/>
				<Item Name="ByteArrayToBitArray.vi" Type="VI" URL="../ByteArrayToBitArray.vi"/>
				<Item Name="ReadBinaryFile.vi" Type="VI" URL="../ReadBinaryFile.vi"/>
			</Item>
			<Item Name="OpenSensorProtocol.vi" Type="VI" URL="../OpenSensorProtocol.vi"/>
			<Item Name="ProcessPacket.vi" Type="VI" URL="../ProcessPacket.vi"/>
			<Item Name="ProcessSerialData.vi" Type="VI" URL="../ProcessSerialData.vi"/>
		</Item>
		<Item Name="Test.vi" Type="VI" URL="../Test.vi"/>
		<Item Name="Dependencies" Type="Dependencies">
			<Item Name="vi.lib" Type="Folder">
				<Item Name="NetComm_UsageReport_report.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/SystemInterfaces/NetworkCommunication/NetComm_UsageReport_report.vi"/>
				<Item Name="NetComm_UsageReport_ResourceType.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/SystemInterfaces/NetworkCommunication/NetComm_UsageReport_ResourceType.ctl"/>
				<Item Name="Trim Whitespace.vi" Type="VI" URL="/&lt;vilib&gt;/Utility/error.llb/Trim Whitespace.vi"/>
				<Item Name="whitespace.ctl" Type="VI" URL="/&lt;vilib&gt;/Utility/error.llb/whitespace.ctl"/>
				<Item Name="WPI_SerialPort.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/SerialPort/WPI_SerialPort.ctl"/>
				<Item Name="WPI_SerialPortGetBytesReceived.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/SerialPort/WPI_SerialPortGetBytesReceived.vi"/>
				<Item Name="WPI_SerialPortOpen.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/SerialPort/WPI_SerialPortOpen.vi"/>
				<Item Name="WPI_SerialPortRead.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/SerialPort/WPI_SerialPortRead.vi"/>
				<Item Name="WPI_SerialPortRef.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/SerialPort/WPI_SerialPortRef.ctl"/>
				<Item Name="WPI_SerialPortTermination.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/SerialPort/WPI_SerialPortTermination.vi"/>
				<Item Name="WPI_UtilitiesFRC Build Error.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Utilities/WPI_UtilitiesFRC Build Error.vi"/>
			</Item>
		</Item>
		<Item Name="Build Specifications" Type="Build"/>
	</Item>
</Project>
