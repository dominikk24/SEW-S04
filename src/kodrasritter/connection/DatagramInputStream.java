package kodrasritter.connection;

import java.io.*;
import java.net.*;

/**
 * Dieser InputStream empfaengt die Pakete vom Netzwerk und wandelt diese in einen Stream um.
 * 
 * Diese Klasse wurde mit wenigen Aenderungen von folgender Seite uerbernommen:
 * http://www.javaworld.com/article/2076519/learn-java/multicast-the-chatwaves.html
 * [abgerufen am 16.11.2014]
 * 
 * @author Mathias Ritter 4AHIT
 * @version 1.0
 *
 */
public class DatagramInputStream extends InputStream {
	byte[] buffer;
	int index, count;
	DatagramSocket socket;
	DatagramPacket packet;

	public DatagramInputStream(DatagramSocket socket) {
		this.socket = socket;
		buffer = new byte[65508];
		packet = new DatagramPacket(buffer, 0);
	}

	public synchronized int read() throws IOException {
		while (index >= count)
			receive();
		return (int) buffer[index++];
	}

	public synchronized int read(byte[] data, int offset, int length)
			throws IOException {
		if (length <= 0)
			return 0;
		while (index >= count)
			receive();
		if (count - index < length)
			length = count - index;
		System.arraycopy(buffer, index, data, offset, length);
		index += length;
		return length;
	}

	public synchronized long skip(long amount) throws IOException {
		if (amount <= 0)
			return 0;
		while (index >= count)
			receive();
		if (count - index < amount)
			amount = count - index;
		index += amount;
		return amount;
	}

	public synchronized int available() throws IOException {
		return count - index;
	}

	private void receive() throws IOException {
		packet.setLength(buffer.length);
		socket.receive(packet);
		index = 0;
		count = packet.getLength();
	}
}
