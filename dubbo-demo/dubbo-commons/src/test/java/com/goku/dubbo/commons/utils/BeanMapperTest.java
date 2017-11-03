package com.goku.dubbo.commons.utils;

import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * BeanMapper Tester.
 *
 * @author fuyongde
 * @version 1.0
 * @since <pre>10/31/2017</pre>
 */
public class BeanMapperTest {

    PersonDTO personDTO;
    List<PersonDTO> personDTOList;

    @Before
    public void before() throws Exception {
        personDTO = new PersonDTO("Jason", 20);
        personDTOList = Lists.newArrayList();
        personDTOList.add(personDTO);
    }

    @After
    public void after() throws Exception {
        personDTO = null;
        personDTOList = null;
    }

    /**
     * Method: map(Object source, Class<T> destinationClass)
     */
    @Test
    public void testMap() throws Exception {
        PersonVO personVO = BeanMapper.map(personDTO, PersonVO.class);
        assertEquals(personDTO.getName(), personVO.getName());
    }

    /**
     * Method: mapList(Collection sourceList, Class<T> destinationClass)
     */
    @Test
    public void testMapList() throws Exception {
        List<PersonVO> personVOList = BeanMapper.mapList(personDTOList, PersonVO.class);
        assertEquals(personDTOList.get(0).getName(), personVOList.get(0).getName());
    }

    /**
     * Method: copy(Object source, Object destinationObject)
     */
    @Test
    public void testCopy() throws Exception {
        PersonVO personVO = new PersonVO();
        BeanMapper.copy(personDTO, personVO);
        assertEquals(personDTO.getName(), personVO.getName());
    }

    @Test
    public void testConver2Map() throws Exception {
        Map<String, Object> map = BeanMapper.conver2Map(personDTO);
        assertEquals(personDTO.getName(), map.get("name"));
        assertEquals(personDTO.getAge(), map.get("age"));
    }

    @Test
    public void testConver2MapString() throws Exception {
        Map<String, String> map = BeanMapper.conver2MapString(personDTO);
        assertEquals(personDTO.getName(), map.get("name"));
        assertEquals(personDTO.getAge(), map.get("age"));
    }

    public static class PersonDTO {
        String name;
        int age;

        public PersonDTO(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class PersonVO {
        String name;
        int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

} 
