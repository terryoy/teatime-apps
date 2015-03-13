/**
 * 
 */
package com.teatime.memo.service.fileimpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.teatime.memo.domain.MemoEntry;
import com.teatime.memo.domain.MemoEntryConstants;
import com.teatime.memo.service.MemoEntryService;
import com.teatime.memo.service.exception.ResourceAccessException;


/**
 * @author Terry Ouyang
 *
 */
public class MemoEntryServiceFileImpl implements MemoEntryService {

	public static final String MONTHLY_FILE_NAME_PATTERN = "'memo'-yyyy-MM.'txt'";
	private static final String SEPARATOR = System.getProperty("line.separator");
	
	private File baseDir = null;
	
	/**
	 * A base directory must be specified before doing memo entry 
	 * business logic.
	 * 
	 * @param dir
	 * @throws ResourceAccessException 
	 */
	public MemoEntryServiceFileImpl(File dir) throws ResourceAccessException {
		if(!dir.exists() || !dir.isDirectory())
			throw new ResourceAccessException("Base directory error");
		
		else this.baseDir = dir;
	}
	
	/* (non-Javadoc)
	 * @see comn.teatime.memo.service.MemoEntryService#createMemoEntry(com.teatime.memo.domain.MemoEntry)
	 */
	public void saveMemoEntry(MemoEntry entry) throws ResourceAccessException {
		File monthlyFile = getMonthlyFile(entry.getDate());
		
		Map<String, MemoEntry> entriesOfMonth = 
			getAllEntriesFromFile(monthlyFile); 
		
		String entryDate = MemoEntryConstants.
			EntryDateTagDafaultFormatter.format(entry.getDate());
		
		// add the entry to the month entries
		entriesOfMonth.put(entryDate, entry);
		
		// persist
		saveEntriesToFile(entriesOfMonth, monthlyFile);		
	}

	/**
	 * @param entriesOfMonth 
	 * @param monthlyFile
	 */
	private void saveEntriesToFile(Map<String, MemoEntry> entriesOfMonth, 
			File monthlyFile) {
		String separator = System.getProperty("line.separator");
		
		// sort the entries by date
		Iterator<Entry<String, MemoEntry>> itr = 
			entriesOfMonth.entrySet().iterator();
		SortedMap<Date, MemoEntry> sortedEntries = 
			new TreeMap<Date, MemoEntry>();
		while(itr.hasNext()) {
			MemoEntry entry = itr.next().getValue();
			sortedEntries.put(entry.getDate(), entry);
		}
		
		try {
			FileWriter writer = new FileWriter(monthlyFile);
			SimpleDateFormat fmt = MemoEntryConstants.EntryDateTagDafaultFormatter;
			
			Iterator<Entry<Date, MemoEntry>> sortedItr = 
				sortedEntries.entrySet().iterator();
			while(sortedItr.hasNext()) {
				MemoEntry entry = sortedItr.next().getValue();
				writer.append(fmt.format(entry.getDate()) + separator);
				writer.append(entry.getContent()); // the content should have included
												   // an enter
				writer.append(MemoEntryConstants.END_OF_ENTRY_LINE + separator);
			}
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param monthlyFile
	 * @return
	 */
	public Map<String, MemoEntry> getAllEntriesFromFile(File monthlyFile) {
		Map<String, MemoEntry> entries = new Hashtable<String, MemoEntry>();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(monthlyFile));
			SimpleDateFormat fmt = 
				MemoEntryConstants.EntryDateTagDafaultFormatter;
			// read file data
			String tmp;
			boolean entryHasBegun = false;
			MemoEntry currentEntry = null;
			StringBuffer contentBuf = null;
			while((tmp = reader.readLine()) != null) {
				// check for a new entry
				if(!entryHasBegun) {
					try {
						Date entryDate = fmt.parse(tmp);
						
						// create new entry
						currentEntry = new MemoEntry();
						currentEntry.setDate(entryDate);
						entryHasBegun = true;
						contentBuf = new StringBuffer();
					} catch (ParseException e) {
						// new entry doesn't begin
					}
					
				}
				else {
					// check end of an entry
					if(!tmp.equals(MemoEntryConstants.END_OF_ENTRY_LINE)) {
						contentBuf.append(tmp + SEPARATOR);
					}
					else {
						entryHasBegun = false;
						currentEntry.setContent(contentBuf.toString());
						entries.put(fmt.format(currentEntry.getDate()), 
								currentEntry);
						
						currentEntry = null;
						contentBuf = null;
					}
				}
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
		
		return entries;
	}

	/* (non-Javadoc)
	 * @see comn.teatime.memo.service.MemoEntryService#deleteMemoEntryByDate(java.util.Date)
	 */
	public void deleteMemoEntryByDate(Date date) throws ResourceAccessException {
		File monthlyFile = this.getMonthlyFile(date);
		
		Map<String, MemoEntry> entriesOfMonth = 
			this.getAllEntriesFromFile(monthlyFile);
		

		SimpleDateFormat fmt = MemoEntryConstants.EntryDateTagDafaultFormatter;
		
		entriesOfMonth.remove(fmt.format(date));
		
		this.saveEntriesToFile(entriesOfMonth, monthlyFile);
	}

	/* (non-Javadoc)
	 * @see comn.teatime.memo.service.MemoEntryService#getMemoEntryByDate(java.util.Date)
	 */
	public MemoEntry getMemoEntryByDate(Date date) throws ResourceAccessException {
		File monthlyFile = getMonthlyFile(date);
		
		Map<String, MemoEntry> entriesOfMonth = 
			this.getAllEntriesFromFile(monthlyFile);
		

		SimpleDateFormat fmt = MemoEntryConstants.EntryDateTagDafaultFormatter;
		
		return entriesOfMonth.get(fmt.format(date));	
	}


	/**
	 * @param date
	 * @return
	 * @throws ResourceAccessException 
	 */
	private File getMonthlyFile(Date date) throws ResourceAccessException {
		
		SimpleDateFormat fmt = new SimpleDateFormat(MONTHLY_FILE_NAME_PATTERN); 
		
		String monthlyFileName = fmt.format(date);
		File monthlyFile = new File(
				baseDir.getAbsolutePath() + "\\" + monthlyFileName);
//		System.out.println("getting file - " + 
//				baseDir.getPath() + "\\" + monthlyFileName);
		
		if(!monthlyFile.exists())
			try {
				monthlyFile.createNewFile();
			} catch (IOException e) {
				throw new ResourceAccessException(e);
			}
		
		return monthlyFile;
	}
}
