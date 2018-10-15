package com.co.mundoviajero.persistence.dao.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

import com.co.mundoviajero.dto.StateDTO;
import com.co.mundoviajero.persistence.dao.IStateDAO;
import com.co.mundoviajero.persistence.dao.impl.StateDAOImpl;
import com.co.mundoviajero.persistence.entity.State;

@RunWith(MockitoJUnitRunner.class)
public class StateDAOImplTest {
	
	@Mock
	IStateDAO iStateDAO;
	
	@Mock
	StateDAOImpl stateDAOImpl;
	
	@Mock
	Query query;
	
	@Mock
	Session session;
	
	private List<StateDTO> statesDTO;
	private List<State> states;
	
	@Before
	public void initComponents() {
		
		statesDTO = new ArrayList<>();
		
		StateDTO stateDTO1 = new StateDTO();
		stateDTO1.setId(1L);
		stateDTO1.setDescription("StateDTO1");
		stateDTO1.setBelongsTo("BelongsTo1");
		
		StateDTO stateDTO2 = new StateDTO();
		stateDTO2.setId(2L);
		stateDTO2.setDescription("StateDTO2");
		stateDTO2.setBelongsTo("BelongsTo2");
		
		statesDTO.add(stateDTO1);
		statesDTO.add(stateDTO2);
		
		states = new ArrayList<>();
		
		State state1 = new State();
		state1.setId(1L);
		state1.setDescription("State1");
		state1.setBelongsTo("BelongsTo1");
		
		State state2 = new State();
		state2.setId(2L);
		state2.setDescription("State2");
		state2.setBelongsTo("BelongsTo2");
		
		states.add(state1);
		states.add(state2);
		
	}
	
	@Test
	public void testGetAllStates() {
		/*
		when(stateDAOImpl.getCurrentSession()).thenReturn(session);
		when(session.createQuery("From State")).thenReturn(query);
		when(query.getResultList()).thenReturn(states);
		
		List<StateDTO> actualStatesDTO = stateDAOImpl.getAllStates();
		
		assertEquals(statesDTO, actualStatesDTO);
		*/
	}

	@Test
	public void testGetState() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCurrentSession() {
		fail("Not yet implemented");
	}

}
