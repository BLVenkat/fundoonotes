package com.bridgelabz.fundoonotes.configuration;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.fundoonotes.entity.Note;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionConfig;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@Configuration
public class CacheConfig {

	@Bean
	public Config hazelCastConfig() {
		return new Config().setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName("NoteCache")
				.setEvictionConfig(new EvictionConfig().setEvictionPolicy(EvictionPolicy.LFU)).
				setTimeToLiveSeconds(2000));
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
