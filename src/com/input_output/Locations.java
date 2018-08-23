package com.input_output;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Locations implements Map<Integer, Location> {

	private static Map<Integer, Location> locations = new HashMap<Integer, Location>();

	public static void main(String[] args) throws IOException {
		// FileWriter locFile = null;

		// ensures that file is closed
		try (FileWriter locFile = new FileWriter("locations_big.txt");
				FileWriter dirFile = new FileWriter("directions_big.txt")) {
			for (Location location : locations.values()) {
				locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
				for (String direction : location.getExits().keySet()) {
					dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction)
							+ "\n");
				}

			}
		}

		// try {
		// locFile = new FileWriter("locations.txt");
		// for (Location location : locations.values()) {
		// locFile.write(location.getLocationID() + "," + location.getDescription() +
		// "\n");
		//// throw new IOException("test exception thrown while writing");
		// }
		//
		// } finally {
		// System.out.println("in finnaly block");
		//
		// if (locFile != null) {
		// System.out.println("Attempting to close locfile");
		// locFile.close();
		//
		// }
		// }
	}

	// executed once when the locations classes loaded
	static {

		Scanner scanner = null;

		try {
			scanner = new Scanner(new FileReader("locations_big.txt"));
			// fields separated by comma
			scanner.useDelimiter(",");
			while (scanner.hasNextLine()) {
				int loc = scanner.nextInt();
				// move pass the comma
				scanner.skip(scanner.delimiter());
				String description = scanner.nextLine();
				System.out.println("Imported loc: " + loc + " :" + description);
				Map<String, Integer> tempExit = new HashMap<>();
				locations.put(loc, new Location(loc, description, tempExit));
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			// when the scanner is closed it is also close method and takes care of closing
			// any stream that it was using
			if (scanner != null) {
				scanner.close();
			}
		}
		// read the exits
		try {
			scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
			scanner.useDelimiter(",");
			while (scanner.hasNextLine()) {
				// int loc = scanner.nextInt();
				// scanner.skip(scanner.delimiter());
				// String direction = scanner.next();
				// scanner.skip(scanner.delimiter());
				// String dest = scanner.nextLine();
				// int destination = Integer.parseInt(dest);
				// reads all line
				String input = scanner.nextLine();
				// creates String array
				String[] data = input.split(",");
				int loc = Integer.parseInt(data[0]);
				String direction = data[1];
				int destination = Integer.parseInt(data[2]);
				System.out.println(loc + " :" + direction + ": " + destination);
				Location location = locations.get(loc);
				location.addExit(direction, destination);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (scanner != null) {

			}
		}

	}

	// Map<String, Integer> tempExit = new HashMap<String, Integer>();
	// locations.put(0, new Location(0, "You are sitting in front of the computer
	// learning Java.", null));
	//
	// tempExit = new HashMap<String, Integer>();
	// tempExit.put("W", 2);
	// tempExit.put("E", 3);
	// tempExit.put("S", 4);
	// tempExit.put("N", 5);
	// locations.put(1,
	// new Location(1, "You are standing at the end of the road before a small brick
	// building.", tempExit));
	// // tempExit.put("Q", 0);
	//
	// tempExit = new HashMap<String, Integer>();
	// tempExit.put("N", 5);
	// locations.put(2, new Location(2, "You are at the top of the hill.",
	// tempExit));
	// // tempExit.put("Q", 0);
	//
	// tempExit = new HashMap<String, Integer>();
	// tempExit.put("W", 1);
	// locations.put(3, new Location(3, "You are inside a building, a well house for
	// small spring.", tempExit));
	// // tempExit.put("Q", 0);
	//
	// tempExit = new HashMap<String, Integer>();
	// tempExit.put("N", 1);
	// tempExit.put("W", 2);
	// locations.put(4, new Location(4, "You are in a valley beside a stream.",
	// tempExit));
	// // tempExit.put("Q", 0);
	//
	// tempExit = new HashMap<String, Integer>();
	// tempExit.put("S", 1);
	// tempExit.put("W", 2);
	// locations.put(5, new Location(5, "You are in the forest.", tempExit));
	// // tempExit.put("Q", 0);

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return locations.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return locations.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return locations.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return locations.containsValue(value);
	}

	@Override
	public Location get(Object key) {
		// TODO Auto-generated method stub
		return locations.get(key);
	}

	@Override
	public Location put(Integer key, Location value) {
		// TODO Auto-generated method stub
		return locations.put(key, value);
	}

	@Override
	public Location remove(Object key) {
		// TODO Auto-generated method stub
		return locations.remove(key);
	}

	@Override
	public void putAll(Map<? extends Integer, ? extends Location> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		locations.clear();
		// TODO Auto-generated method stub

	}

	@Override
	public Set<Integer> keySet() {
		// TODO Auto-generated method stub
		return locations.keySet();
	}

	@Override
	public Collection<Location> values() {
		// TODO Auto-generated method stub
		return locations.values();
	}

	@Override
	public Set<Entry<Integer, Location>> entrySet() {
		// TODO Auto-generated method stub
		return locations.entrySet();
	}

}
