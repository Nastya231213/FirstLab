package com.dao;

import com.entity.Character;
import com.entity.ParticipationLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.databaseConnection.Connect;

public class CharacterDAO implements DatabaseDAO<Character> {
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet res;
	private String sqlQuery;

	@Override
	public void create(Character entity) {
		connection = Connect.getConnection();
		sqlQuery = "insert into characters(role,description,name) values(?,?,?)";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setString(1, entity.getParticipationLevel().getDisplayName());
			pst.setString(2, entity.getDescription());
			pst.setString(3, entity.getName());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}

	}

	public List<Character> findCharactersOfTheBook(int bookId) {
	    List<Character> listCharacters = new ArrayList<>();

	    try (Connection connection = Connect.getConnection()) {
	        String sqlQuery = "SELECT characterId FROM booksandcharacters WHERE bookId=?";
	        try (PreparedStatement pst = connection.prepareStatement(sqlQuery)) {
	            pst.setInt(1, bookId);
	            try (ResultSet res = pst.executeQuery()) {
	                while (res.next()) {
	                    int characterId = res.getInt("characterId");
	                    Character character = find(characterId);
	                    listCharacters.add(character);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	Connect.closeConnection(connection);
	    }

	    System.out.println(listCharacters.size());
	    return listCharacters;
	}

	public void addCharacterToTheBook(int bookId, int characterId) {
		connection = Connect.getConnection();
		sqlQuery = "insert into booksandcharacters(bookId,characterId) values(?,?)";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setInt(1, bookId);
			pst.setInt(2, characterId);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connect.closeConnection(connection);
		}

	}
	public Map<Integer, Integer> getAllCharactersByBookId(int bookId) {
		Map<Integer, Integer> bookAndCharactesIds = new HashMap<>();
		connection = Connect.getConnection();
		sqlQuery = "select * from  booksandcharacters where bookId=?";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setInt(1, bookId);
			res = pst.executeQuery();
			while (res.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return bookAndCharactesIds;
	}
	public Map<Integer, Integer> findConnectedCharactersAndBooks(int characterId,int bookId) {
		Map<Integer, Integer> bookAndCharactesIds = new HashMap<>();
		connection = Connect.getConnection();
		sqlQuery = "select * from  booksandcharacters where characterId=? and bookId=?";
		try {
			pst = connection.prepareStatement(sqlQuery);
			pst.setInt(1, characterId);
			pst.setInt(2, bookId);
			res = pst.executeQuery();
			while (res.next()) {
				bookAndCharactesIds.put(res.getInt(1), res.getInt(2));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return bookAndCharactesIds;
	}
    public List<Character> getAllCharacters() {
    	List<Character> listCharacter=new ArrayList<Character>();
		connection = Connect.getConnection();
		sqlQuery = "select * from  characters";
		try {
			pst=connection.prepareStatement(sqlQuery);
			res=pst.executeQuery();
			while(res.next()) {
				Character character =new Character();
				character.setId(res.getInt(1));
				character.setParticipationLevel(ParticipationLevel.getParticipationLevelByString(res.getString(2)));
			    character.setDescription(res.getString(3));
			    character.setName(res.getString(4));
			    listCharacter.add(character);		    
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listCharacter;
    }
	
	@Override
	public Character find(int id) {
	    Character character = null;

	    try (Connection connection = Connect.getConnection()) {
	        String sqlQuery = "SELECT * FROM characters WHERE id=?";
	        try (PreparedStatement pst = connection.prepareStatement(sqlQuery)) {
	            pst.setInt(1, id);
	            try (ResultSet res = pst.executeQuery()) {
	                while (res.next()) {
	                    character = new Character();
	                    character.setId(res.getInt(1));
	                    character.setParticipationLevel(ParticipationLevel.getParticipationLevelByString(res.getString(2)));
	                    character.setDescription(res.getString(3));
	                    character.setName(res.getString(4));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return character;
	}
	@Override
	public void update(Character entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

}
