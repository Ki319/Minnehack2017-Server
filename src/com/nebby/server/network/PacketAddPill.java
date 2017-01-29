package com.nebby.server.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.nebby.server.Server1;

public class PacketAddPill extends Packet
{
	
	private String pillName;
	private String pillDosage;
	private String pillInterval;
	
	public PacketAddPill() {}

	public PacketAddPill(String name, String dosage, String time)
	{
		pillName = name;
		pillDosage = dosage;
		pillInterval = time;
	}
	
	@Override
	public void writeData(DataOutputStream output) throws IOException 
	{
		output.writeUTF(pillName);
		output.writeUTF(pillDosage);
		output.writeUTF(pillInterval);
	}

	@Override
	public void copy(DataInputStream data) throws IOException
	{
		pillName = data.readUTF();
		pillDosage = data.readUTF();
		pillInterval = data.readUTF();
	}

	@Override
	public void handle(Network network)
	{
		Server1 server = ((ServerNetwork)network).getServer();
		server.addPillToUser(network, pillName, pillDosage, pillInterval);
	}

}
