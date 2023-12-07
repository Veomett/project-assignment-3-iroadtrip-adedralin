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
    
    class Node {
    	//Node class used for findPath
        String country;
        int distance;

        public Node(String country, int distance) {
            this.country = country;
            this.distance = distance;
        }
    }

    public int getDistance(String country1, String country2) {
        country1 = edgeCase(country1);
        country2 = edgeCase(country2);

        if (!borderCountries.containsKey(country1) || !borderCountries.containsKey(country2)) {
            // Either country1 or country2 does not exist
            return -1;
        }

        HashMap<String, Double> distances = borderCountries.get(country1);

        if (distances == null || !distances.containsKey(country2)) {
            // There is no direct land border between country1 and country2
            return -1;
        }

        return distances.get(country2).intValue();
    }

    public List<String> findPath(String country1, String country2) {
    	//handle edge cases for country names
        country1 = edgeCase(country1);
        country2 = edgeCase(country2);

        // Dijkstra's algorithm to find the shortest path
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        Set<String> visited = new HashSet<>();
        Map<String, String> previous = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();

        //Add starting node to priority queue with 0 for distance
        priorityQueue.add(new Node(country1, 0));

        //Process nodes in priority queue until empty
        while (!priorityQueue.isEmpty()) {
        	//Get node with smallest distance from priority queue
            Node current = priorityQueue.poll();
            String currentCountry = current.country;

            //Skip processing if node already visited
            if (visited.contains(currentCountry)) {
                continue;
            }
            
            //marks current node as visited
            visited.add(currentCountry);

            if (currentCountry.equals(country2)) {
                // Reconstruct the path
                List<String> path = new ArrayList<>();
                while (previous.containsKey(currentCountry)) {
                    path.add(0, currentCountry + " --> " + previous.get(currentCountry) +
                            " (" + distances.get(currentCountry) + " km.)");
                    currentCountry = previous.get(currentCountry);
                }
                //returns path
                return path;
            }
            
            //Get neighbors of current node
            HashMap<String, Double> neighbors = borderCountries.get(currentCountry);
            
            //Goes through each neighbor
            if (neighbors != null) {
                for (String neighbor : neighbors.keySet()) {
                	//Skip processing if neighbor is already visited
                    if (!visited.contains(neighbor)) {
                    	//calculate new distance from starting node to neighbor
                        int newDistance = distances.getOrDefault(currentCountry, 0) +
                                neighbors.get(neighbor).intValue();
                        
                        //Updates distance and prev node if new distance is shorter
                        if (!distances.containsKey(neighbor) || newDistance < distances.get(neighbor)) {
                            distances.put(neighbor, newDistance);
                            previous.put(neighbor, currentCountry);
                            //Adds neighbor to priority queue with new distance
                            priorityQueue.add(new Node(neighbor, newDistance));
                        }
                    }
                }
            }
        }

        // No path found
        return Collections.emptyList();
    }
    
    public String idCheck(String id) {
        // Additional cases may be added based on edge cases
        if ("YEM".equals(id)) {
            id = "YAR";
        } else if ("UKG".equals(id)) {
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
    			//has bordering countries
    			String[] second = first[1].split("; ");
    			//splits up each bordering country including its distance from main Country
    			
    			for (int i = 0; i < second.length; i++) {
    				//runs through each bordering country
    				String secondTrim = second[i].trim().replaceAll(",", "");
    				//removes outside whitespace and deletes commas
    				secondTrim = secondTrim.substring(0, secondTrim.length() - 3);
    				//removes " km" from each entry   				
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
    				//bordering country distance from main Country (double)
    				borderCountryDist.put(thirdOne, Double.parseDouble(thirdTwo));
    				//adds bordering countries into inner HashMap
    			}    			
    			borderCountries.put(first[0], borderCountryDist);
    			//adds main Country and a hashMap of its bordering countries with distance values
    			borderCountryDist.clear();
    		}
    	}
    	scan.close();
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
    		}
    		if (skip == false) {
    			
    			name = edgeCase(name);
    			borderCountryDist = borderCountries.get(name);
    			 //inner HashMap is set to find itself given the main Country in main HashMap
    			count++;
    			if (borderCountryDist != null) {
    				for (String key : borderCountryDist.keySet()) {
    					//looks at each country(key) in inner HashMap
    					String id = nametoId.get(key);
    					//takes country(key) and converts it to ID
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
    				}
    			}
    		}
    	}
    	scan.close();
    }


    public static void main(String[] args) throws FileNotFoundException {
        IRoadTrip a3 = new IRoadTrip(args);

        a3.acceptUserInput();
    }
}