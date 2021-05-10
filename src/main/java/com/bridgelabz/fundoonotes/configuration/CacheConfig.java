package com.bridgelabz.fundoonotes.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.fundoonotes.entity.Note;
import com.hazelcast.config.Config;
import com.hazelcast.config.ManagementCenterConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class CacheConfig {

	@Bean
	public Config hazelCastConfig() {
		return new Config().setManagementCenterConfig(new ManagementCenterConfig());
	}

	@Bean

	public HazelcastInstance hazelcastInstance(Config hazelCastConfig) {

		return Hazelcast.newHazelcastInstance(hazelCastConfig);

	}

	@Bean
	public Map<String, List<Note>> noteMap(HazelcastInstance hazelcastInstance) {

		return hazelcastInstance.getMap("notes");

	}

}
