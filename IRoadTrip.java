import java.util.*;
import java.io.*;

public class IRoadTrip {
	
	//implicit declaration of global variables
	String[] files;
	HashMap<String, Double> borderCountryDist;
	HashMap<String, HashMap<String, Double>> borderCountries;
	HashMap<String, String> countryKeys;
	HashMap<Integer, String[]> numCheck;
	HashMap<String, String> idtoName;
	HashMap<String, String> nametoId;

    public IRoadTrip (String [] args) {
    	files = args; //files via arguments
    	borderCountryDist = new HashMap<String, Double>();
    	//inner HashMap
    		//<country, distance from main Hashmap key country>
    	borderCountries = new HashMap<String, HashMap<String, Double>>();
    	//main HashMap
    		//<country, HashMap of Bordering Countries>
    	countryKeys = new HashMap<String, String>();
    	//Keys/Names/Aliases for countries
    		//<Keys/Names/Aliases, country key in main HashMap>
    	numCheck = new HashMap<Integer, String[]>();
    	//checks country number to confirm it exists and is legal country
    		//<num (int), Names/Aliases
    	idtoName = new HashMap<String, String>();
    	
    	nametoId = new HashMap<String, String>();
    	
    }

    public int getDistance (String country1, String country2) {
    	//only accounts for direct capital to capital distance
        return -1;
    }

    public List<String> findPath (String country1, String country2) {
    	//dijkstra's algorithm
    	//origin and destination get passed in
    	//have empty priority queue
    	//each element in prioitity queue will be hashmap
    		//make hashmap of type string (full path), int (length of path)
    		//string is path, int is int for whole path
    		

        // No path found
        return null;
    }
    
    public String idCheck(String id) {
    	if (id.equals("YEM")) {
    		id = "YAR";
    	}
    	else if (id.equals("UKG")) {
    		id = "UK";
    	}
    	return id;
    }
    
    public String edgeCase(String name) {
    	
    	if (name.equals("Cambodia (Kampuchea)")) {
    		name = "Cambodia";
    	}
    	else if (name.equals("East Timor")) {
    		name = "Timor-Leste";
    	}
    	else if (name.equals("Vietnam, Democratic Republic of")) {
    		name = "Vietnam";
    	}
    	else if (name.equals("Sri Lanka (Ceylon)")) {
    		name = "Sri Lanka";
    	}
    	else if (name.equals("Myanmar (Burma)")) {
    		name = "Burma";
    	}
    	else if (name.equals("Korea, Republic of")) {
    		name = "Korea, South";
    	}
    	else if (name.equals("Korea, People's Republic of")) {
    		name = "Korea, North";
    	}
    	else if (name.equals("Kyrgyz Republic")) {
    		name = "Kyrgyzstan";
    	}
    	else if (name.equals("Yemen (Arab Republic of Yemen)")) {
    		name = "Yemen";
    	}
    	else if (name.equals("Turkey (Ottoman Empire)")) {
    		name = "Turkey (Turkiye)";
    	}
    	else if (name.equals("Iran (Persia)")) {
    		name = "Iran";
    	}
    	else if (name.equals("Zimbabwe (Rhodesia)")) {
    		name = "Zimbabwe";
    	}
    	else if (name.equals("Tanzania/Tanganyika")) {
    		name = "Tanzania";
    	}
    	else if (name.equals("Congo, Democratic Republic of (Zaire)")) {
    		name = "Congo, Democratic Republic of the";
    	}
    	else if (name.equals("Congo")) {
    		name = "Congo, Republic of the";
    	}
    	else if (name.equals("Swaziland")) {
    		name = "Eswatini";
    	}
    	else if (name.equals("Burkina Faso (Upper Volta)")) {
    		name = "Burkina Faso";
    	}
    	else if (name.equals("Cote D’Ivoire")) {
    		name = "Cote d'Ivoire";
    	}
    	else if (name.equals("Gambia")) {
    		name = "Gambia, The";
    	}
    	else if (name.equals("Cape Verde")) {
    		name = "Cabo Verde";
    	}
    	else if (name.equals("Belarus (Byelorussia)")) {
    		name = "Belarus";
    	}
    	else if (name.equals("Russia (Soviet Union)")) {
    		name = "Russia";
    	}
    	else if (name.equals("Rumania")) {
    		name = "Romania";
    	}
    	else if (name.equals("Bosnia-Herzegovina")) {
    		name = "Bosnia and Herzegovina";
    	}
    	else if (name.equals("Macedonia (Former Yugoslav Republic of)")) {
    		name = "North Macedonia";
    	}
    	else if (name.equals("Italy/Sardinia")) {
    		name = "Italy";
    	}
    	else if (name.equals("Czech Republic")) {
    		name = "Czechia";
    	}
    	else if (name.equals("German Federal Republic")) {
    		name = "Germany";
    	}
    	else if (name.equals("Surinam")) {
    		name = "Suriname";
    	}
    	else if (name.equals("Bahamas")) {
    		name = "Bahamas, The";
    	}
    	else if (name.equals("United States of America")) {
    		name = "United States";
    	}
    	else {
    		name = name;
    	}
    	return name;
    }
    
    public void acceptUserInput() throws FileNotFoundException {
    	Scanner scan = new Scanner(new File(files[0]));
    	//reading borders.txt
    	
    	int c = 0;
    	
    	while (scan.hasNextLine()) {
    		//reads line by line
    		String line = scan.nextLine();
    		String[] first = line.split(" = ");
    		//splits main Country and bordering countries + distances from main Country
    		
    		if (first.length == 1) {
    			//case for no bordering countries
    			borderCountries.put(first[0], null);
    		}
    		else {
    			//System.out.println(first[1]);
    			//has bordering countries
    			String[] second = first[1].split("; ");
    			//splits up each bordering country including its distance from main Country
    			
    			for (int i = 0; i < second.length; i++) {
    				//runs through each bordering country
    				String secondTrim = second[i].trim().replaceAll(",", "");
    				//removes outside whitespace and deletes commas
    				secondTrim = secondTrim.substring(0, secondTrim.length() - 3);
    				//removes " km" from each entry
    				
    				//System.out.println(secondTrim);
    				
    				int digitI = 0;
    				for (int j = 0; j < secondTrim.length(); j++) {
    					//runs through country entry
    					if (Character.isDigit(secondTrim.charAt(j))) {
    						//finds index of first number to act as split point
    						digitI = j;
    						//saves appropriate index
    						break;
    					}
    				}
    				String thirdOne = secondTrim.substring(0, digitI);
    				//bordering country name
    				String thirdTwo = secondTrim.substring(digitI);
    				
    				//System.out.println(thirdOne);
    				//System.out.println(thirdTwo);
    				//bordering country distance from main Country (double)
    				borderCountryDist.put(thirdOne, Double.parseDouble(thirdTwo));
    				//adds bordering countries into inner HashMap
    			}
    			c++;
    			System.out.println(borderCountryDist + "    " + c + "  " + first[0]);
    			
    			//HashMap<String, Double> temp = borderCountryDist;
    			borderCountries.put(first[0], borderCountryDist);
    			//System.out.println(borderCountries + "   " + c + "   ");
    			
    			//System.out.println(borderCountries);
    			//borderCountryDist.clear();
    			System.out.println(borderCountries.get(first[0])+ " first[0]");
    			//adds main Country and a hashMap of its bordering countries with distance values
    		}
    	}
    	scan.close();
    	
    	
    	for (int l = 0; l < borderCountries.size(); l++) {
    		//System.out.println()
    	}
    	//System.out.println(borderCountries);
    	//stops scanning borders.txt file

    	scan = new Scanner(new File(files[2]));

    	//reading state_name file
    	HashMap<String, String> idtoName = new HashMap<String, String>();
    	//allows for transfer of info of ID of country to name of country
    	HashMap<String, String> nametoId = new HashMap<String, String>();
    	//allows for transfer of info of name of country to ID of country
    	String wasteLine = scan.nextLine();
    	//removes title line, has no readable info

    	while (scan.hasNextLine()) {
    		//reads line by line
    		String line = scan.nextLine();
    		String[] first = line.split("\t");
    		
    		String[] list = new String[5];
    		//splits all info per line by tab
    		if (first[4].equals("2020-12-31")) {
    			//if date is current, put into main HashMap
    			list[0] = first[1];
    			list[1] = first[2];
    			
    			numCheck.put(Integer.parseInt(first[0]), list);
    			
    			first[1] = idCheck(first[1]);
    			
    			idtoName.put(first[1], first[2]);
    			//adds countries ID to name
    			nametoId.put(first[2], first[1]);
    			//adds countries name to ID
    		}
    	}
    	scan.close();
    	//stops reading state_name file

    	scan = new Scanner(new File(files[1]));
    	//reading capdist file
    	wasteLine = scan.nextLine();
    	//removes title line, has no readable info

    	int count = 0;
    	while (scan.hasNextLine()) {
    		//reads line by line
    		String line = scan.nextLine();
    		
    		String[] first = line.split(",");
    		//splits all info by , per line
    		String name = idtoName.get(first[1]);
    		//given ID from capdist file, acquires its name from other HashMap based on state_name file
    	
    		boolean skip = false;
    		if (name == null) {
    			String[] aliases = numCheck.get(Integer.parseInt(first[0]));
    			if (aliases == null) {
    				skip = true;
    				continue;
    			}
    			else {
    				name = aliases[0];
    			}	
    		//ACCOUNT FOR NULL CASES
    		}
    		if (skip == false) {
    			
    			name = edgeCase(name);
    			
    			//System.out.println(name);
    			borderCountryDist = borderCountries.get(name);
    			 //inner HashMap is set to find itself given the main Country in main HashMap
    			count++;
    			if (borderCountryDist != null) {
    				//System.out.println(name);
    				//System.out.println(borderCountries.get(name));
    				for (String key : borderCountryDist.keySet()) {
    					//looks at each country(key) in inner HashMap
    					String id = nametoId.get(key);
    					
    					if (id == null) {
    						//System.out.println(key);
    					}
    					//System.out.println(id);
    					//takes country(key) and converts it to ID
    					//account for edge cases
    					//System.out.println(first[3] + " first at 3");
    					
    					if (id == first[3]) {
    						
    						//if ID = destination in capdist file
    						String dist = first[4];
    						//save the distance
    						borderCountryDist.put(key, Double.parseDouble(dist));
    						//replace distance as value for inner HashMap
    						borderCountries.put(name, borderCountryDist);
    						
    						borderCountryDist.clear();
    						//change main HashMap accordingly
    					}
    					else {
    						//System.out.println(id);
    					}
    				}
    			}
    				//if main Country HAS bordering countries
    		}
    		//if ID is same as ID in state_name file, IF NULL, NEED TO ACCOUNT FOR EDGE CASE
    	}
    	scan.close();
    }


    public static void main(String[] args) throws FileNotFoundException {
        IRoadTrip a3 = new IRoadTrip(args);

        a3.acceptUserInput();
    }
}

/*
System.out.println("\nborderCountries HashMap:");
for (Map.Entry<String, HashMap<String, Double>> entry : borderCountries.entrySet()) {
    String country = entry.getKey();
    HashMap<String, Double> distances = entry.getValue();

    System.out.println("Country: " + country);
    System.out.println("Distances: " + distances);
    System.out.println();
}
*/
// read state_name.tsv file
	//make another hash map (id to fullname)
	//string string USA to United States of America
	//make another hash map (fullname to id)
	//string string United States of America to USA
	//reading each line of statename
		//if end date = dec 31 2020, add to map
		//else dont add to map
	//split based on tabs
	//
// read capdist excel file
	//split read each line base on commans
	//get acronym for first country, (a)
	//use one previous hash maps to get full name of given country
	//check to see if that has an associated value (any bordering countries)
	//if yes
		//iterate over that hashmap
		//get key , [i]th element, from the inside hashmap
		//get id for the key
		//check to see if id is in the destination part of capdist
		//if match get distance number and convert to int
		//will now overwrite the double value in Main hashmap, both the outer and inner just for the hit country

//be sure names are matching between state name and border
	//use ifs
	//stay consistent with names
	//need a way to go through name to acronym
		//will use state_name to do this

/*
if (name == null) {
	use countryKeys HashMap
	
	populate countryKeys checking state_name and capdist file
		use country number to get every name and ID for each country
		put these values in a list and add them to countryKeys 
		countryKeys <name and IDs, main Country>
		main Country = how country is spelled in main HashMap from borders.txt
	
	checks for different potential values for a country
	if completed correctly, no name will be null at this stage
}
*/