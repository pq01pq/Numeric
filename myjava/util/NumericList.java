package myjava.util;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NumericList extends ArrayList<Numeric> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6713704302991084497L;
	
	// 1. 숫자형식의 인스턴스들만 추가될 수 있도록 함
	
	// fields
	
	// constructors
	
	// constructor from super class
	public NumericList() {
		super();
	}
	// constructor from super class
	public NumericList(int initialCapacity) {
		super(initialCapacity);
	}
		
	public NumericList(byte... byteArray) {
		super();
		for(int i = 0; i < byteArray.length; i++) {
			super.add(new Numeric(byteArray[i]));
		}
	}
	public NumericList(short... shortArray) {
		super();
		for(int i = 0; i < shortArray.length; i++) {
			super.add(new Numeric(shortArray[i]));
		}
	}
	public NumericList(int... intArray) {
		super();
		for(int i = 0; i < intArray.length; i++) {
			super.add(new Numeric(intArray[i]));
		}
	}
	public NumericList(long... longArray) {
		super();
		for(int i = 0; i < longArray.length; i++) {
			super.add(new Numeric(longArray[i]));
		}
	}
	public NumericList(float... floatArray) {
		super();
		for(int i = 0; i < floatArray.length; i++) {
			super.add(new Numeric(floatArray[i]));
		}
	}
	public NumericList(double... doubleArray) {
		super();
		for(int i = 0; i < doubleArray.length; i++) {
			super.add(new Numeric(doubleArray[i]));
		}
	}
	
	public NumericList(Number[] numberArray) {
		super();
		for(int i = 0; i < numberArray.length; i++) {
			Numeric numeric = new Numeric(numberArray[i]);
			if(numeric.isRealNumber()) {
				super.add(numeric);
			}
		}
	}
	public NumericList(String[] stringArray) {
		super();
		for(int i = 0; i < stringArray.length; i++) {
			Numeric numeric = new Numeric(stringArray[i]);
			if(numeric.isRealNumber()) {
				super.add(numeric);
			}
		}
	}
	
	public NumericList(Collection<?> objects) {
		super();
		numericList(objects.toArray());
	}
	public NumericList(Object[] objectArray) {
		super();
		numericList(objectArray);
	}
	public NumericList(Object object) {
		super();
		numericList(object);
	}
	
	private boolean numericList(Object[] objectArray) {
		if(objectArray == null || objectArray.length == 0) {
			return false;
		}
		boolean isSucceedAll = true;
		for(int i = 0; i < objectArray.length; i++) {
			if(!numericList(objectArray[i])) {
				isSucceedAll = false;
			}
		}
		return isSucceedAll;
	}
	private boolean numericList(Object object) {
		if(object == null) {
			return false;
		}
		if(object.getClass().isArray()) {
			Object[] objectArray;
			try {
				objectArray = (Object[]) object;
			} catch(ClassCastException e) {
				objectArray = Batch.unfoldPrimitiveTypeArray(object);
			}
			return numericList(objectArray);
		} else if(object instanceof Collection) {
			Collection<?> objects = (Collection<?>) object;
			return numericList(objects.toArray());
		} else {
			if(object.getClass() == String.class) {
				String string = (String) object;
				return this.add(string);
			} else if(object instanceof Number) {
				Number number = (Number) object;
				return this.add(number);
			} else if(object.getClass() == Numeric.class) {
				Numeric numeric = (Numeric) object;
				return this.add(numeric);
			} else {
				return false;
			}
		}
	}
	
	// instance methods
	@Override
	public boolean add(Numeric numeric) {
		if(numeric.isRealNumber()) {
			return super.add(numeric);
		} else {
			return false;
		}
	}
	public boolean add(String string) {
		Numeric numeric = new Numeric(string);
		if(numeric.isRealNumber()) {
			return super.add(numeric);
		} else {
			return false;
		}
	}
	public boolean add(Number number) {
		if(number != null) {
			return super.add(new Numeric(number));
		} else {
			return false;
		}
	}
	
	@Override
	public void add(int index, Numeric numeric) {
		if(numeric.isRealNumber()) {
			super.add(index, numeric);
		}
	}
	public void add(int index, String string) {
		if(Numerics.isRealNumber(string)) {
			super.add(index, new Numeric(string));
		}
	}
	public void add(int index, Number number) {
		if(number != null) {
			super.add(index, new Numeric(number));
		}
	}
	
	@Override
	public boolean addAll(Collection<? extends Numeric> numerics) {
		boolean isSucceedAll = true;
		for(Numeric numeric : numerics) {
			isSucceedAll = this.add(numeric) && isSucceedAll;
		}
		return isSucceedAll;
	}
	public boolean addAll(Object[] objectArray) {
		return numericList(objectArray);
	}
	public boolean addAll(Object object) {
		return numericList(object);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends Numeric> numerics) {
		boolean isSucceedAll = true;
		List<Numeric> trueNumerics = new ArrayList<>();
		for(Numeric numeric : numerics) {
			if(numeric.isRealNumber()) {
				trueNumerics.add(numeric);
			} else {
				isSucceedAll = false;
			}
		}
		return super.addAll(index, trueNumerics) && isSucceedAll;
	}
	public boolean addAll(int index, Object[] objectArray) {
		if(objectArray == null || objectArray.length == 0) {
			return false;
		}
		List<Numeric> trueNumerics = trueNumerics(new ArrayList<Numeric>(), objectArray);
		boolean areSucceedAll = super.addAll(index, trueNumerics);
		if(objectArray.length == trueNumerics.size()) {
			return true && areSucceedAll;
		} else {
			return false;
		}
	}
	public boolean addAll(int index, Object object) {
		List<Numeric> trueNumerics = trueNumerics(new ArrayList<Numeric>(), object);
		return super.addAll(index, trueNumerics);
	}
	
	private List<Numeric> trueNumerics(List<Numeric> templateList, Object[] appendArray) {
		if(appendArray != null && appendArray.length > 0) {
			for(int i = 0; i < appendArray.length; i++) {
				templateList = trueNumerics(templateList, appendArray[i]);
			}
		}
		return templateList;
	}
	private List<Numeric> trueNumerics(List<Numeric> templateList, Object appendant) {
		if(appendant == null) {
			return templateList;
		}
		if(appendant.getClass().isArray()) {
			Object[] objectArray;
			try {
				objectArray = (Object[]) appendant;
			} catch(ClassCastException e) {
				objectArray = Batch.unfoldPrimitiveTypeArray(appendant);
			}
			return trueNumerics(templateList, objectArray);
		} else if(appendant instanceof Collection) {
			Collection<?> objects = (Collection<?>) appendant;
			return trueNumerics(templateList, objects.toArray());
		} else {
			Numeric numeric = null;
			if(appendant.getClass() == String.class) {
				String string = (String) appendant;
				numeric = new Numeric(string);
			} else if(appendant instanceof Number) {
				Number number = (Number) appendant;
				numeric = new Numeric(number);
			} else if(appendant.getClass() == Numeric.class) {
				numeric = (Numeric) appendant;
			}
			if(numeric != null && numeric.isRealNumber()) {
				templateList.add(numeric);
			}
			return templateList;
		}
	}
	
	@Override
	public Numeric set(int index, Numeric numeric) {
		if(numeric.isRealNumber()) {
			return super.set(index, numeric);
		} else {
			return null;
		}
	}
	
	@Override
	public void replaceAll(UnaryOperator<Numeric> operator) {
		super.replaceAll(operator);
		clean();
	}
	@Override
	public void forEach(Consumer<? super Numeric> action) {
		super.forEach(action);
		clean();
	}
	private void clean() {
		int i = 0;
		while(i < size()) {
			if(!get(i).isRealNumber()) {
				remove(i);
				i--;
			}
			i++;
		}
	}
	
	public List<String> getStrings() {
		List<String> list = new ArrayList<>();
		for(Numeric numeric : this) {
			list.add(numeric.getString());
		}
		return list;
	}
	public List<Number> getNumbers() {
		List<Number> list = new ArrayList<>();
		for(Numeric numeric : this) {
			list.add(numeric.getNumber());
		}
		return list;
	}
	
	public void sort() {
		Collections.sort(this);
	}
	
	
	// 2. 단순 합
	
	// instance methods
	
	public Number sum() {
		if(this.areIntegers()) {
			long longSum = 0L;
			for(Numeric numeric : this) {
				longSum += numeric.getNumber().longValue();
			}
			if(longSum < (long)Integer.MAX_VALUE) {
				return (int)longSum;
			} else {
				return longSum;
			}
		} else {
			double doubleSum = 0.0;
			for(Numeric numeric : this) {
				doubleSum += numeric.getNumber().doubleValue();
			}
			return doubleSum;
		}
	}
	
	// 3. 숫자형식 판별
	
	// instance methods
	
	// 자연수
	public boolean areNaturalNumbers() {
		if(this.size() == 0) {
			return false;
		}
		for(Numeric numeric : this) {
			if(!numeric.isNaturalNumber()) {
				return false;
			}
		}
		return true;
	}

	// 정수
	public boolean areIntegers() {
		if(super.size() == 0) {
			return false;
		}
		for(Numeric numeric : this) {
			if(!numeric.isInteger()) {
				return false;
			}
		}
		return true;
	}
	
	// 실수인 것만 리스트에 포함되게 하였으므로
	// 실수 판별 instance method는 필요하지 않다.
}
