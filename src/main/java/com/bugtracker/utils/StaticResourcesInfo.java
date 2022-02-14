package com.bugtracker.utils;

import java.io.IOException;
import java.util.*;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/** Holds information about the static resources. */
public class StaticResourcesInfo {

	private final Set<String> resources;

	private final Map<String, String> filenameLookup;

	public StaticResourcesInfo(ClassLoader cl) throws IOException {
		resources = new HashSet<>();
		filenameLookup = new HashMap<>();

		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
		Resource[] res = resolver.getResources("classpath:/static/*");

		for (Resource r : res) {
			String filename = r.getFilename();
			if (filename == null) {
				throw new IOException("Filename of static resource cannot be null");
			}
			resources.add(filename);

			String[] fileParts = filename.split("\\.");
			if (fileParts.length > 2) {
				continue;
			}

			String filenameWithoutExt = fileParts[0];
			if (filenameLookup.containsKey(filenameWithoutExt)) {
				filenameLookup.remove(filenameWithoutExt);
			} else {
				filenameLookup.put(filenameWithoutExt, filename);
			}
		}
	}

	/**
	 * Returns the location to which a request to a static resource should be
	 * forwarded to. May return an empty optional if the destination is already
	 * correct.
	 */
	public Optional<String> forward(String filename) {
		if (resources.contains(filename)) {
			return Optional.empty();
		}

		String lookup = filenameLookup.get(filename);
		if (lookup == null) {
			return Optional.of("index.html");
		}
		return Optional.of(lookup);
	}
}
