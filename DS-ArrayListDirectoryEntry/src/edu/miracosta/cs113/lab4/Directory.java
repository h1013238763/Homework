package edu.miracosta.cs113.lab4;


import java.util.ArrayList;
import java.util.List;

// Lab Exercise 4


public class Directory {
	private List<DirectoryEntry> theDirectory =
			new ArrayList<>();
	

	/** Add an entry to theDirectory or change an existing entry.
	 * @param aName The name of the person being added or changed
	 * @param newNumber The new number to be assigned
	 * @return The old number , or if a new entry, null
	 */
	public String addOrChangeEntry(String aName, String newNumber) {
		DirectoryEntry dE;
		String oldNumber = null;
		int index = find(aName);

		if (index != -1)
			dE = theDirectory.get(index);
		else
			dE = null;
		
		if(dE==null) 
			theDirectory.add(new DirectoryEntry(aName,newNumber));
		else {
			oldNumber = dE.getNumber();
			theDirectory.get(index).setNumber(newNumber);
		}
		return oldNumber;
	}

	/** Find an entry in the directory.
	@param name The name to be found
	@return The index of the entry with the requested name.
	If the name is not in the directory, returns -1
	*/
		private int find(String name) {
			int index = 0;
			for (DirectoryEntry d : theDirectory) {
				if(d.getName().equals(name))
					return index;
				index++;
			}
			return -1;
		}
	/** Remove an entry.
	@param aName The name of the person being removed
	@return The entry removed, or null if there is no entry for aName
	*/

		public DirectoryEntry removeEntry(String aName) {
			DirectoryEntry dE;
			int index = find(aName);
			if(index != -1) {
				dE = theDirectory.get(index);
				theDirectory.remove(theDirectory.remove(index));
				return dE;
			}else
				return null;
		}
}
