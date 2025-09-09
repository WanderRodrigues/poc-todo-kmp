# App de Lugares Turísticos - Kotlin Multiplatform

[![official project](http://jb.gg/badges/official.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

Este é um aplicativo Kotlin Multiplatform que exibe uma lista de lugares turísticos famosos ao redor do mundo. O app foi desenvolvido como uma prova de conceito (POC) para demonstrar as capacidades do KMP (Kotlin Multiplatform) com Compose Multiplatform.

## 📱 Demonstração do App

### Android
https://github.com/WanderRodrigues/poc-todo-kmp/blob/main/videos/android.webm

### iOS  
https://github.com/WanderRodrigues/poc-todo-kmp/blob/main/videos/ios.mp4

## 📱 Funcionalidades

- **Lista de Lugares**: Exibe uma grade de lugares turísticos com imagens, nomes, localização e avaliações
- **Detalhes Completos**: Tela de detalhes com informações abrangentes sobre cada lugar
- **Multiplataforma**: Funciona tanto no Android quanto no iOS
- **UI Moderna**: Interface desenvolvida com Compose Multiplatform
- **Navegação**: Sistema de navegação entre lista e detalhes

## 🏗️ Arquitetura

O aplicativo utiliza uma arquitetura limpa com as seguintes camadas:

- **UI Layer**: Compose Multiplatform para interface do usuário
- **Presentation Layer**: ViewModels para gerenciamento de estado
- **Domain Layer**: Repositórios para lógica de negócio
- **Data Layer**: APIs e armazenamento local

## 🛠️ Tecnologias Utilizadas

- **Kotlin Multiplatform**: Compartilhamento de código entre plataformas
- **Compose Multiplatform**: UI declarativa multiplataforma
- **Ktor**: Cliente HTTP para requisições de rede
- **kotlinx.serialization**: Serialização JSON
- **Coil**: Carregamento de imagens
- **Koin**: Injeção de dependência
- **Compose Navigation**: Navegação entre telas

## 📊 Dados

O aplicativo consome dados de um arquivo JSON contendo informações sobre 10 lugares turísticos famosos:

- Torre Eiffel (Paris, França)
- Coliseu (Roma, Itália)
- Cristo Redentor (Rio de Janeiro, Brasil)
- Muralha da China (Pequim, China)
- Machu Picchu (Cusco, Peru)
- Estátua da Liberdade (Nova York, EUA)
- Taj Mahal (Agra, Índia)
- Pirâmides de Gizé (Gizé, Egito)
- Big Ben (Londres, Reino Unido)
- Sydney Opera House (Sydney, Austrália)

Cada lugar contém informações detalhadas como:
- Dados básicos (nome, cidade, país, descrição)
- Imagens e coordenadas geográficas
- Informações históricas (ano de construção, altura)
- Dados comerciais (preços, horários de funcionamento)
- Avaliações e dificuldade de acesso
- Informações de acessibilidade
- Contatos e websites

## 🚀 Como Executar

### Pré-requisitos

- Android Studio Hedgehog ou superior
- Xcode 15+ (para iOS)
- JDK 17+
- Gradle 8.0+

### Android

1. Abra o projeto no Android Studio
2. Selecione o dispositivo Android ou emulador
3. Execute o comando:
   ```bash
   ./gradlew :composeApp:installDebug
   ```

### iOS

1. **Build do framework Kotlin para iOS**:
   ```bash
   ./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
   ```

2. **Abra o projeto no Xcode**:
   ```bash
   open iosApp/iosApp.xcodeproj
   ```

3. **Ou execute via linha de comando**:
   ```bash
   xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -destination 'platform=iOS Simulator,name=iPhone 15 Pro' build
   ```

### Executar via Gradle

Para Android:
```bash
./gradlew :composeApp:installDebug
```

Para iOS (após build do framework):
```bash
./gradlew :composeApp:iosSimulatorArm64Test
```

## 📁 Estrutura do Projeto

```
composeApp/
├── src/
│   ├── commonMain/
│   │   ├── kotlin/com/jetbrains/kmpapp/
│   │   │   ├── data/           # Camada de dados
│   │   │   │   ├── PlaceObject.kt      # Modelo de dados
│   │   │   │   ├── PlaceApi.kt         # Interface da API
│   │   │   │   ├── PlaceRepository.kt  # Repositório
│   │   │   │   └── PlaceStorage.kt     # Armazenamento
│   │   │   ├── di/             # Injeção de dependência
│   │   │   │   └── Koin.kt
│   │   │   ├── screens/        # Telas da aplicação
│   │   │   │   ├── list/       # Tela de lista
│   │   │   │   └── detail/     # Tela de detalhes
│   │   │   └── App.kt          # Aplicação principal
│   │   └── composeResources/   # Recursos compartilhados
│   ├── androidMain/            # Código específico do Android
│   └── iosMain/               # Código específico do iOS
├── build.gradle.kts           # Configuração do módulo
└── ...
```

## 🔧 Configuração

### Dependências Principais

O projeto utiliza as seguintes dependências principais:

```kotlin
// Compose Multiplatform
implementation(compose.runtime)
implementation(compose.foundation)
implementation(compose.material3)
implementation(compose.ui)

// Navegação
implementation(compose.navigation)

// Networking
implementation(libs.ktor.client.core)
implementation(libs.ktor.client.content.negotiation)
implementation(libs.ktor.serialization.kotlinx.json)

// Serialização
implementation(libs.kotlinx.serialization.json)

// Imagens
implementation(libs.coil3.compose)

// DI
implementation(libs.koin.core)
implementation(libs.koin.compose)
```

## 📱 Screenshots

O aplicativo apresenta:

1. **Tela de Lista**: Grade de lugares com imagens, nomes, localização e avaliações
2. **Tela de Detalhes**: Informações completas sobre cada lugar turístico

## 🧪 Testes

Para executar os testes:

```bash
# Testes unitários
./gradlew test

# Testes de integração
./gradlew connectedAndroidTest

# Testes iOS (após build do framework)
./gradlew :composeApp:iosSimulatorArm64Test
```

## 📋 Checklist de Entrega

- [x] ✅ Aplicativo funcional em Android e iOS
- [x] ✅ Interface moderna com Compose Multiplatform
- [x] ✅ Navegação entre telas implementada
- [x] ✅ Consumo de dados JSON externo
- [x] ✅ Arquitetura limpa com separação de responsabilidades
- [x] ✅ Injeção de dependência configurada
- [x] ✅ Documentação completa no README
- [x] ✅ Instruções de build e execução
- [x] ✅ Estrutura de projeto organizada
- [x] ✅ Dados ricos e informativos sobre lugares turísticos

## 🎯 Objetivos Alcançados

Este projeto demonstra:

1. **Kotlin Multiplatform**: Compartilhamento eficiente de código entre Android e iOS
2. **Compose Multiplatform**: UI declarativa e moderna
3. **Arquitetura Limpa**: Separação clara de responsabilidades
4. **Integração de Dados**: Consumo de API externa com tratamento de erros
5. **Navegação**: Sistema de navegação fluido entre telas
6. **UI/UX**: Interface intuitiva e responsiva

## 📞 Contato

Desenvolvido como POC para demonstração das capacidades do Kotlin Multiplatform.

---

**Nota**: Este projeto foi desenvolvido como uma prova de conceito para demonstrar as capacidades do Kotlin Multiplatform na criação de aplicações móveis multiplataforma com interface moderna e arquitetura limpa.
