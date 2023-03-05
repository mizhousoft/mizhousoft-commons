package com.mizhousoft.commons.io.zip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import com.mizhousoft.commons.io.ZipException;
import com.mizhousoft.commons.lang.CharEncoding;

/**
 * Zip压缩工具类
 *
 * @version
 */
public abstract class ZipCompressUtils
{
	public static void compress(String destFilePath, String srcFilePath, boolean ignoreRoot) throws ZipException
	{
		compress(new File(destFilePath), new File(srcFilePath), ignoreRoot);
	}

	public static void compress(File destFile, File srcFile, boolean ignoreParent) throws ZipException
	{
		if (!srcFile.exists())
		{
			throw new ZipException("File not exist.");
		}

		try (OutputStream ostream = new FileOutputStream(destFile);
		        BufferedOutputStream bufferedOStream = new BufferedOutputStream(ostream);
		        ZipArchiveOutputStream zipArchiveStream = new ZipArchiveOutputStream(bufferedOStream);)
		{
			zipArchiveStream.setEncoding(CharEncoding.UTF8_NAME);
			zipArchiveStream.setUseZip64(Zip64Mode.AsNeeded);

			if (ignoreParent && srcFile.isDirectory())
			{
				File[] files = srcFile.listFiles();
				if (files != null)
				{
					for (File file : files)
					{
						putArchiveFile(zipArchiveStream, file, "");
					}
				}
			}
			else
			{
				putArchiveFile(zipArchiveStream, srcFile, "");
			}
		}
		catch (IOException e)
		{
			throw new ZipException("Zip compress failed.", e);
		}
	}

	private static void putArchiveFile(ZipArchiveOutputStream zipArchiveStream, File srcFile, String base) throws IOException
	{
		String entryName = base + srcFile.getName();
		ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(srcFile, entryName);
		zipArchiveStream.putArchiveEntry(zipArchiveEntry);

		if (srcFile.isFile())
		{
			try (FileInputStream fileInputStream = new FileInputStream(srcFile);)
			{
				IOUtils.copy(fileInputStream, zipArchiveStream);
				zipArchiveStream.closeArchiveEntry();
			}
		}
		else
		{
			zipArchiveStream.closeArchiveEntry();

			File[] files = srcFile.listFiles();
			if (files != null)
			{
				for (File file : files)
				{
					putArchiveFile(zipArchiveStream, file, entryName + "/");
				}
			}
		}
	}
}
