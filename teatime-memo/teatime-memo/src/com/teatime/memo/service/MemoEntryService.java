/**
 * 
 */
package com.teatime.memo.service;

import java.util.Date;

import com.teatime.memo.domain.MemoEntry;
import com.teatime.memo.service.exception.ResourceAccessException;

/**
 * The service for memo entries.
 * 
 * @author Terry Ouyang
 *
 */
public interface MemoEntryService {

	public MemoEntry getMemoEntryByDate(Date date) throws ResourceAccessException;
	
	public void saveMemoEntry(MemoEntry entry) throws ResourceAccessException;
	
	public void deleteMemoEntryByDate(Date date) throws ResourceAccessException;
}
