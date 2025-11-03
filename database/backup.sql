-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: ecoimpacto
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_articles`
--

DROP TABLE IF EXISTS `tb_articles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_articles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `link` varchar(1024) DEFAULT NULL,
  `publish_date` varchar(255) DEFAULT NULL,
  `content` text,
  `image_url` varchar(1024) DEFAULT NULL,
  `author` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_articles`
--

LOCK TABLES `tb_articles` WRITE;
/*!40000 ALTER TABLE `tb_articles` DISABLE KEYS */;
INSERT INTO `tb_articles` VALUES (1,'Criança provoca incêndio em residência no bairro Bethânia em Itabira - As Notícias Online','https://asnoticiasonline.com.br/2025/09/16/crianca-provoca-incendio-em-residencia-no-bairro-bethania-em-itabira/','2025-09-16 18:45:50','... amianto). O incêndio foi controlado pela guarnição, que também realizou ... Apesar dos prejuízos materiais, não houve vítimas.','https://asnoticiasonline.com.br/wp-content/uploads/2025/09/a22438fb-134c-43ce-9c60-462edb64daf7.jpg','As Notícias Online'),(2,'&#39;Câncer do 11 de setembro&#39;: entenda doença que atinge sobreviventes e socorristas','https://www.sbtnews.com.br/noticia/saude/cancer-do-11-de-setembro-entenda-doenca-que-atinge-sobreviventes-e-socorristas','2025-09-11 11:00:20','A mistura incluía amianto, sílica, metais pesados e compostos cancerígenos, que permaneceram em Manhattan e no Brooklyn por semanas. Incêndios que...','https://sbt-news-assets-prod.s3.sa-east-1.amazonaws.com/Atentado_as_torres_gemeas_ainda_causa_doencas_em_pessoas_Robert_Levine_Wikimedia_Commons_63b4e563ed.png','SBT News'),(3,'24 anos depois: câncer já mata mais que o 11 de Setembro em Nova York - A Tribuna','https://www.atribuna.com.br/noticias/mundo/24-anos-depois-cancer-ja-mata-mais-que-o-11-de-setembro-em-nova-york-1.478967','2025-09-11 09:23:15','... amianto, chumbo, dioxinas e fibras de vidro liberados no colapso dos edifícios. Nos anos seguintes, médicos passaram a observar altos índices de...','https://www.atribuna.com.br/image/policy:1.478968:1757592780/image.jpg?f=3x2','A Tribuna'),(4,'Tombamento de Veículo de Carga Deixa Ferido e Causa Interdição em Presidente Olegário','https://paracatunews.com.br/noticia/71389/tombamento-de-veiculo-de-carga-deixa-ferido-e-causa-interdicao-em-presidente-olegario','2025-09-11 08:33:02','Um caminhão do tipo “Romeu e Julieta”, carregado com telhas de amianto, tombou após o condutor perder o controle do veículo. Continua após a...','https://paracatunews.com.br/images/noticias/71389/88da8d26186a1b1759fa35bf84f150c2.webp','Noticias Paracatu News'),(5,'Câncer ligado ao 11 de setembro já causa mais mortes que ataques - Folha do Noroeste','https://folhadonoroeste.com.br/noticias-agora/cancer-ligado-ao-11-de-setembro-ja-causa-mais-mortes-que-ataques/','2025-09-11 08:20:09','Após os ataques de 11 de setembro de 2001, nuvens tóxicas se espalharam em Nova York, contendo substâncias perigosas como amianto, sílica, metais...','https://folhadonoroeste.com.br/wp-content/uploads/2025/09/ataque-terrorista-nos-eua-11-de-setembro-torres-gemeas-torre-do-wtc-em-nova-york-1694435385806_v2_61.jpeg','folhadonoroeste.com.br'),(6,'Bombeiros combatem ocorrência de incêndio próxima ao Presídio Sebastião Satiro - Patos Já','https://www.patosja.com.br/meio%20ambiente/bombeiros-combatem-ocorrencia-de-incendio-proxima-ao-presidio-sebastiao-satiro%C2%A0','2025-09-11 04:24:27','Veículo que transportava telhas de amianto tomba na MGC 354 em Presidente Olegário. O motorista sofreu fratura em um dos dedos e rompimento no...','https://apipatosja.patosja.com.br/images/1757532168746.webp','Patos Já'),(7,'Carreta Romeu e Julieta tomba na MGC-354 e motorista fica ferido, em Presidente Olegário','https://clubenoticia.com.br/carreta-romeu-e-julieta-tomba-na-mgc-354-e-motorista-fica-ferido-em-presidente-olegario/','2025-09-11 04:17:52','... amianto. O veículo tombou após o motorista, de 43 anos, perder o controle direcional. O condutor foi socorrido ao Pronto-Socorro de Presidente...','https://clubenoticia.com.br/wp-content/uploads/2025/09/WhatsApp-Image-2025-09-10-at-11.07.05-1.jpeg','Clube Noticia - Notícias de Patos de Minas e região'),(9,'Acidente de trânsito com vítimas - Patos em Destaque','https://patosemdestaque.com.br/noticia/1Lib8os0eZ','2025-09-11 02:10:34','... amianto. A caçamba do reboque caiu na faixa de domínio lado direito da pista, no sentido em que transitava. O responsável pela empresa, acionou o...','https://patosemdestaque.com.br/fotos/noticias/4efb3dcccbbf4cb13a6e84734ff50566_G.jpg','Patos em Destaque'),(10,'Presidente Olegário • Caminhão tomba e motorista fica ferido na MGC-354 - Patos Notícias','https://patosnoticias.com.br/caminhao-tomba-e-motorista-fica-ferido-na-mgc-354/','2025-09-11 02:03:54','O acidente provocou derramamento da carga de telhas de amianto sobre a pista e a faixa de domínio. A caçamba do reboque caiu na lateral direita da...','https://i0.wp.com/patosnoticias.com.br/wp-content/uploads/2025/09/whatsapp-image-2025-09-10-at-11-07-05-4fac.jpeg?fit=1067%2C800&ssl=1','Patos Notícias (PN)'),(11,'Carreta carregada que seguia de Lagamar com telhas de <b>amianto</b> tomba na MGC-354 em ...','https://montanheza.com.br/carreta-carregada-que-seguia-de-lagamar-com-telhas-de-amianto-tomba-na-mgc-354-em-presidente-olegario/','2025-09-11 00:43:40','A Polícia Militar Rodoviária foi acionada na madrugada desta quarta-feira (10) e, se deslocou ao km 150 da.','https://montanheza.com.br/wp-content/uploads/2025/09/0d7e050e-e146-4df2-ba82-6719b218c949-1.webp','montanheza.com.br'),(13,'Trabalhador cai de telhado e fica ferido durante manutenção em Catalão - Mais Goiás','https://www.maisgoias.com.br/cidades/trabalhador-cai-de-telhado-e-fica-ferido-durante-manutencao-em-catalao/','2025-09-10 16:10:37','Ele realizava manutenção no telhado do terminal rodoviário do posto JK quando uma telha de amianto se rompeu, provocando o acidente. Segundo...','https://via.placeholder.com/200',NULL),(14,'Motorista fica ferido após carreta carregada com telhas de <b>amianto</b> tombar na MG-354','https://pohoje.com.br/motorista-fica-ferido-apos-carreta-carregada-com-telhas-de-amianto-tombar-na-mg-354/','2025-09-10 15:05:56','... amianto. O motorista, de 43 anos, foi socorrido ao Pronto Socorro do Hospital Municipal Darci José Fernandes em Presidente Olegário, com uma...','https://pohoje.com.br/wp-content/uploads/2025/09/a23bdda9-dc2c-44fe-9775-328ccca98168.jpg','PO Hoje'),(15,'Johnson &amp; Johnson deve pagar US$ 950 milhões por câncer ligado ao uso de talco','https://capitalist.com.br/johnson-johnson-deve-pagar-us-950-milhoes-por-cancer-ligado-ao-uso-de-talco/','2025-10-11 02:56:34','... vítima de mesotelioma. O câncer ... A ação afirma que os produtos continham fibras de amianto, e o júri responsabilizou diretamente a companhia.','https://capitalist.com.br/wp-content/uploads/2023/07/talco-1-1000x600.jpg','Capitalist'),(16,'Johnson &amp; Johnson deve pagar US$ 1 bilhão por talco cancerígeno - Tribuna de Minas','https://tribunademinas.com.br/colunas/maistendencias/johnson-johnson-deve-pagar-us-1-bilhao-por-talco-cancerigeno/','2025-10-10 18:51:02','... vítima de mesotelioma, um câncer raro ligado à exposição ao amianto. O júri dividiu a indenização em duas partes: US$ 16 milhões destinados a...','https://tribunademinas.com.br/colunas/maistendencias/wp-content/uploads/2025/10/talco.jpg','Mais Tendências - Tribuna de Minas'),(17,'MPF considera ilegítima atuação de Associação de <b>Vítimas</b> de Vacinas e Medicamentos em ...','https://www.mpf.mp.br/ac/sala-de-imprensa/noticias-ac/mpf-considera-ilegitima-atuacao-de-associacao-de-vitimas-de-vacinas-e-medicamentos-em-acao-judicial','2025-10-13 15:05:48','... amianto na Bahia &middot; Covid-19 &middot; Derramamento de óleo na costa brasileira ... Acre. Página Inicial &middot; Sala de Imprensa &middot; Notícias; MPF considera ilegítima...','https://www.mpf.mp.br/ac/sala-de-imprensa/noticias-ac/mpf-considera-ilegitima-atuacao-de-associacao-de-vitimas-de-vacinas-e-medicamentos-em-acao-judicial/@@images/c030141a-92bb-46e5-8e4f-9db772e785fe.jpeg','Portal MPF');
/*!40000 ALTER TABLE `tb_articles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_empresas`
--

DROP TABLE IF EXISTS `tb_empresas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_empresas` (
  `id_empresa` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `endereco` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `coordenadas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_empresas`
--

LOCK TABLES `tb_empresas` WRITE;
/*!40000 ALTER TABLE `tb_empresas` DISABLE KEYS */;
INSERT INTO `tb_empresas` VALUES (1,'﻿Nova Ambiental','Estr. Aracariguama, 751 - Estância São Francisco, Itapevi - SP, CEP: 06695-560','(11) 4144-4655','','https://www.novaambiental.com.br/sobre-a-empresa/','-46.966731,-23.551383'),(2,'LigEntulho','Rua  Wilhein Winter, 500, Jundiaí - SP, CEP: 13213-000','(11) 4587-7966','ligentulho@ligentulho.com.br','https://ligentulho.com.br/','-46.879403,-23.178727'),(3,'Seven Residuos','Rua Vargas, 284 Cidade Satélite Guarulhos – SP, CEP: 07231-300','(11) 2308-2716','suporte@sevenresiduos.com.br','https://sevenresiduos.com.br/','-46.533467,-23.463858'),(4,'Demolidora Lobato','Av. Paulista, 171, 4º andar - EVO 19 - SP, CEP: 01311-904; Rua José Versolato, 111 Torre B, 6° Andar, Sala 617, São Bernardo do Campo - SP, CEP: 09750-730','(11) 4342-1117, (11) 95777-6167, (11) 4357-4436','demolidora@demolidoralobato.com.br','https://www.dmlobato.com.br/','-46.548948,-23.691505'),(5,'Demolix','Av. Brig. Faria Lima, 3144 - Jardim Paulistano, São Paulo - SP, CEP: 01451-000','(11) 4861-1174','contato@demolix.com.br','https://www.demolix.com.br/','-46.663713,-23.570533'),(6,'FS Demolições','Av. João Batista Medina, 272 - Jardim Maranhao, Embu das Artes - SP, CEP: 06803-447','(11) 2579-4619, (11) 94792-5037','contato@fsdemolicoes.com.br','https://www.fsdemolicoes.com.br/','-46.862697,-23.646659'),(7,'N3 Demolidora','Rua Dr. José de Moura Resende, 27 - Butantã - SP, CEP: 05517-000','(11) 3726-1300, (11) 3726-1300','comercial@n3demolicoes.com.br','https://www.n3demolicoes.com.br/','-46.723484,-23.570418'),(8,'Demolidora Dutra','R. Nossa Senhora Do Outeiro, 410 - Sala 02 - SP, CEP: 04807-010','(11) 5923-6761, (11) 95205-8221, (11) 94071-5255','contato@demolidoradutra.com.br','https://www.demolidoradutra.com.br/','-48.428808,-22.154376'),(9,'Santos Demolidora','Av. Horácio Lafer, 247 - Jardim das Flores, Osasco - SP, CEP: 06112-010','(11) 4187-6685, (11) 94058-1405','contato@santosdemolidora.com.br','https://www.santosdemolidora.com.br/','-46.795662,-23.535566'),(10,'Demolidora M.R.E','R. dos Alpes, 203 - Cambuci, São Paulo - SP, CEP: 01520-030','(11) 3207-3976, (11) 98255-4713','contato@demolidoramre.com.br','https://www.demolidoramre.com.br/','-46.613481,-23.574231'),(11,'RMO Demolidora','R. Francisco Marengo, 500 - Tatuapé, São Paulo - SP, CEP: 03313-000','(11) 4872-8318, (11) 95998-8376','contato@rmodemolidora.com.br','https://rmodemolidora.com.br/','-46.563345,-23.532777'),(12,'JFR Demolições e Terraplanagem','Rua Reginaldo Nilson da Silva, 298, Osasco - SP, CEP:  06268-170','(11) 3456-7890,  (11) 91014-8090, (11) 93456-7890, \n(11) 95856-2962','demolidoraterraplanagemjfr@gmail.com','https://demolidoraterraplanagemjfr.com.br/','-46.785824,-23.499499'),(13,'Novo Horizonte Ambiental','R. Caldas Novas, 105 - Bethaville I, Barueri - SP, CEP: 06404-301','(11) 3911-2307, (11) 97544-2982','leandro@novohorizonteambiental.com.br','https://www.novohorizonteambiental.com.br/','-46.869304,-23.505832'),(14,'JR Demolições','Avenida São Camilo, 3347, Condomínio Alphaville Granja Viana - São Paulo - SP, CEP: 06708-735','(11) 4169-6072, (11) 98722-5210','jr@jrdemolicoes.com.br','https://www.jrdemolicoes.com.br/','-46.846486,-23.577286'),(15,'Nobre Demolidora','Av. Nova Cantareira, 1984 - 5ª andar - Tucuruvi - SP, CEP: 02330-003','(11) 2628-0795, (11) 93018-0580','atendimento@nobredemolidora.com.br','https://nobredemolidora.com.br/','-46.611143,-23.479811'),(16,'Demolidora Santos Filho','R. Urupês, 228 - Jardim Santo Eduardo, Embu - SP, CEP: 06823-140','(11) 4149-6313,  (11) 4244-2227, (11) 94239-6887','contato@demolidorasantosfilho.com.br','https://www.demolidorasantosfilho.com.br/','-46.798821,-23.673498'),(17,'RMP Recicladora de Entulho','Av. Marginal do Rio Jundiaí nº 2400, Bairro Industrial - SP,  CEP: 13.221-800','(11) 4493-2260','operacional@rmprecicladoradeentulho.com.br','https://rmprecicladoradeentulho.com.br/','-46.860778,-23.202793'),(18,'Cetes Ambiental','Av. Jorge Bei Maluf, 529 – Bloco C - Vila Theodoro, Suzano - SP - CEP: 08686-000','(11) 4747-5523, (11) 2630-0194, (11) 98905-7133, (11) 93447-3309, (11) 98822-0307','sac@cetes.eco.br','https://www.cetesambiental.com.br/','-46.306511,-23.534403'),(19,'Formiga - Coleta e Gerenciamento Ambiental','Estrada das Mimosas, 78 - Santa Maria, Osasco - SP, CEP: 06150-550','(11) 3691-1052, (11) 99851-3654, (11) 94714-9567','comercial@1aformiga.com.br, comercialentulho@1aformiga.com.br','https://www.1aformiga.com.br/','-51.219535,-29.692722'),(20,'Grupo Lixotal','Avenida Doutor Felipe Pinel , 750 Pirituba - São Paulo - SP, CEP: 02939-000','(11) 3973-2211','contato@gupolixotal.com.br','http://www.grupolixotal.com.br/','-46.736275,-23.4698'),(21,'Mega Ambiental','Rua José Martins Fernandes, 435 São Bernardo do Campo - SP, CEP: 09843-400','(11) 4332-5566, (11) 95177-3355','megaambiental@megaambiental.com','https://megaambiental.com/','-46.590036,-23.770128'),(22,'Eco Trans Ambiental','Rua Horácio Vergueiro Rudge, 157 - São Paulo - SP, CEP: 02512-060','(11) 3438-7888, (11) 2691-1221','comercial@ecotransambiental.com.br','https://www.ecotransambiental.com.br/','-46.663501,-23.513544'),(23,'TWM Ambiental','R. Dr. José Alexandre Crosgnac, 398, Itapevi - SP','(11) 2222-1500','atendimento@twmambiental.com.br','https://twmambiental.com.br/','-46.966731,-23.551383'),(24,'GreenView Engenharia & Consultoria Ambiental','Alameda Santos, 415 - 10º andar - Cerqueira César, São Paulo - SP, CEP: 01419-913','(11) 4810-5696','comercial@greenviewgv.com.br','https://greenviewgv.com.br/','-46.648579,-23.569767'),(25,'Koletus','Rua Ester Fernandes Morgado, 120, São João Clímaco - SP, CEP: 04244-060','(11) 2946-7964, (11) 2946-6911, (11) 94009-9351 - ID:45*12*18180, (11) 94734-0610 - ID: 35*70*38256, (11) 94782-3925, (11) 94734-0610','vendas@koletus.com.br','https://www.koletus.com.br/','-47.75,-21.4');
/*!40000 ALTER TABLE `tb_empresas` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-14 16:27:54
