package com.nebby.server.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketQueryPills extends Packet
{
	
	private String[] pillNames;
	
	public PacketQueryPills() {}
	
	public PacketQueryPills(String[] names)
	{
		pillNames = names;
	}

	@Override
	public void writeData(DataOutputStream output) throws IOException
	{
		output.writeInt(pillNames.length);
		for(String s : pillNames)
		{
			output.writeUTF(s);
		}
	}

	@Override
	public void copy(DataInputStream data) throws IOException 
	{
		
	}

	@Override
	public void handle(Network network) 
	{
		((ServerNetwork) network).getServer().pillsQuery(network);
	}

}