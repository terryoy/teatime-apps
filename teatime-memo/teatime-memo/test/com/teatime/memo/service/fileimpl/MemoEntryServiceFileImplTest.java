/**
 * 
 */
package com.teatime.memo.service.fileimpl;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

import com.teatime.memo.domain.MemoEntry;
import com.teatime.memo.service.exception.ResourceAccessException;

/**
 * @author Terry Ouyang
 *
 */
public class MemoEntryServiceFileImplTest {

	MemoEntryServiceFileImpl memoEntryServiceFileImpl;
	
	public MemoEntryServiceFileImplTest() {
		super();
		try {
			memoEntryServiceFileImpl =
				new MemoEntryServiceFileImpl(new File("."));
		} catch (ResourceAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test method for {@link com.teatime.memo.service.fileimpl.MemoEntryServiceFileImpl#getAllEntriesFromFile(java.io.File)}.
	 * @throws Exception 
	 */
	@Test
	public void testGetAllEntriesFromFile() throws Exception {
		File file = new File("aaa.txt");
		
		Map<String, MemoEntry> entries = 
			memoEntryServiceFileImpl.getAllEntriesFromFile(file);
		
		Iterator<String> itr = entries.keySet().iterator();
		while(itr.hasNext()) {
			String entryDate = itr.next();
			String content = entries.get(entryDate).getContent();
			
			System.out.println(entryDate);
			System.out.println(content);
		}
	}

	/**
	 * Test method for {@link com.teatime.memo.service.fileimpl.MemoEntryServiceFileImpl#saveMemoEntry(com.teatime.memo.domain.MemoEntry)}.
	 * @throws Exception 
	 */
	@Test
	public void testSaveMemoEntry() throws Exception {
		MemoEntry entry = new MemoEntry();
		entry.setDate(new Date());
		entry.setContent("1.Today we have a meeting.");
		memoEntryServiceFileImpl.saveMemoEntry(entry);
		
	}
}
