package com.bugtracker.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/** Holds information about the static resources. */
public class StaticResourcesInfo {

	private final Set<String> resources;
	private final Map<String, String> filenameLookup;

	public static final String RESOURCES_FOLDER = "resources";
	private static final Logger LOGGER = LoggerFactory.getLogger(StaticResourcesInfo.class);

	public StaticResourcesInfo(ClassLoader cl) throws IOException {
		resources = new HashSet<>();
		filenameLookup = new HashMap<>();

		fillStorage(cl);
	}

	private void fillStorage(ClassLoader cl) throws IOException {
		Resource[] resources = resolveResources(cl);
		for (Resource r : resources) {
			String filename = r.getFilename();
			if (filename == null) {
				throw new IOException("Filename of static resource cannot be null");
			}
			this.resources.add(filename);

			String[] fileParts = filename.split("\\.");
			if (fileParts.length > 2) {
				continue;
			}

			String filenameWithoutExt = fileParts[0];
			filenameLookup.put(filenameWithoutExt, filename);
		}
	}

	private Resource[] resolveResources(ClassLoader cl) throws IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
		try {
			return resolver.getResources("classpath:/static/" + RESOURCES_FOLDER + "/*");
		} catch (FileNotFoundException e) {
			LOGGER.warn("No static resources provided (Exception: '" + e.getMessage() + "')");
			return new Resource[] {};
		}
	}

	/**
	 * Returns the location to which a request to a static resource should be
	 * forwarded to. May return an empty optional if the destination is already
	 * correct.
	 */
	public Optional<String> forward(String filename) {
		if (resources.contains(filename)) {
			return Optional.of(filename);
		}

		String lookup = filenameLookup.get(filename);
		if (lookup == null) {
			return Optional.of("index.html");
		}
		return Optional.of(lookup);
	}
}
