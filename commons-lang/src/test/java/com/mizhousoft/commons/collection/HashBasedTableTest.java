package com.mizhousoft.commons.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * HashBasedTable Test
 *
 * @version
 */
public class HashBasedTableTest
{
	@Test
	public void test()
	{
		Table<Integer, Integer, Integer> table = HashBasedTable.create(1);

		table.put(1, 1, 1);
		Assertions.assertEquals(1, table.size());

		table.remove(1);
		Assertions.assertEquals(0, table.size());
	}
}
