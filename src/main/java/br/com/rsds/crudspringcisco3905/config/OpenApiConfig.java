package br.com.rsds.crudspringcisco3905.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "Sawagger Cisco 3905 - OpenAPI 3.0", 
				description = "Essa é uma API de configuração de ramal em telefones IP Cisco modelo 3905.",
//				termsOfService = "",
		contact = @Contact(
						name = "Ricardo",
						url = "https://github.com/ricardosantanadev4",
				        email = "ricardosantanadev4@gmail.com"
//						extensions = @Extension(
//								properties = @ExtensionProperty(
//										name = "", 
//										value = "", 
//										parseValue = false
//										)
//								)
		),
//		license = @License(
//				name = "",
//				url = "",
//				extensions = @Extension(
//						properties = @ExtensionProperty(
//								name = "", 
//								value = "", 
//								parseValue = false
//								)
//						),
//				identifier = ""
//				),
		version = "1.0"
//		extensions = @Extension(
//				name = "", 
//				properties = @ExtensionProperty( 
//						name = "", 
//						value = "", 
//						parseValue = false
//						)
//				),
//		summary = ""
				),
//		tags = @Tag(
//				name = "",
//				description = "",
//				externalDocs = @ExternalDocumentation(
//						description = "", 
//						url = "", 
//						extensions = @Extension(
//								name = "", 
//								properties = @ExtensionProperty(
//										name = "", 
//										value = "", 
//										parseValue = false
//										)
//								)
//						),
//				extensions = @Extension(
//						name = "", 
//						properties = @ExtensionProperty(
//								name = "", 
//								value = "", 
//								parseValue = false 
//								)
//						) 
//				),
				servers = { 
						@Server(
								url = "http://localhost:8080",
								description = "Local ENV"
//								variables = @ServerVariable(
//										name = "",
//										allowableValues = "",
//										defaultValue = "",
//										description = "",
//										extensions = @Extension(
//												properties = @ExtensionProperty(
//														name ="",
//														value = "", 
//														parseValue = false
//														)
//												)
//										),
//								extensions = @Extension(
//										name = "", 
//										properties = @ExtensionProperty(
//												name = "",
//												value = "", 
//												parseValue = false 
//												)
//										)
								),
						@Server(
								url = "http://192.168.1.9:8080",
								description = "PROD ENV"
//								variables = @ServerVariable(
//										name = "",
//										allowableValues = "",
//										defaultValue = "",
//										description = "",
//										extensions = @Extension(
//												properties = @ExtensionProperty(
//														name ="",
//														value = "", 
//														parseValue = false
//														)
//												)
//										),
//								extensions = @Extension(
//										name = "", 
//										properties = @ExtensionProperty(
//												name = "",
//												value = "", 
//												parseValue = false 
//												)
//										)
								)
						}
//		security = @SecurityRequirement(
//						name = "", 
//						scopes = {"", ""}
//						),
//		externalDocs = @ExternalDocumentation(
//				description = "",
//				url = "",
//				extensions = @Extension(
//						name = "",
//						properties = @ExtensionProperty(
//								name = "", 
//								value = "", 
//								parseValue = false
//								)
//						)
//				),
//		extensions = @Extension(
//				name = "", 
//				properties = @ExtensionProperty(
//						name = "", 
//						value = "", 
//						parseValue = false
//						) 
//				)
		)

public class OpenApiConfig {

}
