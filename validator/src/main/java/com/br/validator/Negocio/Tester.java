package com.br.validator.Negocio;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

import com.br.validator.annotations.Length;
import com.br.validator.annotations.NotEmpty;
import com.br.validator.annotations.PasswordTest;
import com.br.validator.annotations.Validate;
import com.br.validator.exceptions.*;

public class Tester {
		public void validate(Object o)  {
			Class<?> clazz = o.getClass();
			
			for(Field f : clazz.getDeclaredFields()) {
				f.setAccessible(true);
				Object value;
				try {
					value = f.get(o);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
				Annotation[] anotacoes = f.getAnnotations();
				for(Annotation annotation : anotacoes)	{
					if(annotation != null) {
						if(annotation.annotationType() == NotEmpty.class) {
							if(value.toString().isEmpty())
								throw new NotEmptyException("O campo " + f.getName() + " eh obrigatorio e nao pode ser vazio"); 
						}
						if(annotation.annotationType() == Length.class) {
							Length lengthAnnotation = (Length)annotation;
							int tamanho = value.toString().length();
							if(tamanho < lengthAnnotation.min() || tamanho > lengthAnnotation.max()) {
								throw new LengthException("O tamanho do " + f.getName() + " deve ser entre " + lengthAnnotation.min() + " e " + lengthAnnotation.max()); 
							}
						}
						if(annotation.annotationType() == Validate.class) {
							Validate validateAnnotation = (Validate)annotation;
							switch(validateAnnotation.type()) {
								case EMAIL:
									if(!Pattern.matches("[a-zA-Z0-9_.]+@[a-zA-Z0-9_]+\\.[a-z.]*", value.toString()))
										throw new ValidateException("O email informado é invalido"); 
						
									break;
								default:
									throw new UnsupportedOperationException("Implementar validacao para " + validateAnnotation.type().toString());
							}
						}
						if(annotation.annotationType() == PasswordTest.class) {
							String message = "";
							PasswordTest passwordTest = (PasswordTest)annotation;
							if(passwordTest.needsNonAlphanumeric()) {
								if(!Pattern.matches(".*[^\\w]+.*", value.toString())) 
									message += "A senha deve ter um caracter não alfanumerico ex: !@#$%¨&* \n";						
							}
							if(passwordTest.needsNumber()) {
								if(!Pattern.matches(".*[0-9]+.*", value.toString())) 
									message += "A senha deve ter um numero \n";						
							}
							if(passwordTest.needsUpperCase()) {
								if(!Pattern.matches(".*[A-Z]+.*", value.toString())) 
									message += "A senha deve ter uma letra maiscula \n";		
							}
							if(message.length() > 0) {
								throw new PasswordTestException(message);
							}
						}
					}
				}
			}
		}

}
