<img src="https://user-images.githubusercontent.com/45483768/143955773-61ec00b4-47ca-4973-a013-35aaaf7f1f65.png" align="left"/>

# Riskrieg | Map

[![License: MIT](https://img.shields.io/badge/License-MIT-white.svg)](https://github.com/Riskrieg/map/blob/main/LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/com.riskrieg/map.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.riskrieg%22%20AND%20a:%22map%22)
[![Donate](https://img.shields.io/badge/Donate-PayPal-lightgreen.svg)](https://paypal.me/aaronjyoder)

Looking for the core Riskrieg repository? Find it [here](https://github.com/Riskrieg/core).

## About

This repository hosts the code for the map portion of the project. It also contains the current definition of the RKM file format.

## Download
[![Maven Central](https://img.shields.io/maven-central/v/com.riskrieg/map.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.riskrieg%22%20AND%20a:%22map%22)

**Maven**
```xml
<dependency>
  <groupId>com.riskrieg</groupId>
  <artifactId>map</artifactId>
  <version>VERSION</version>
</dependency>
```

**Gradle**
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("com.riskrieg:map:VERSION")
}
```

## RKM File Format

The RKM file format is a binary file format used to store data about Riskrieg maps. The format is totally open and free for anyone to use or modify for any purpose. You can find a Java implementation of an encoder and decoder in the [codec](https://github.com/Riskrieg/codec) repository.

### Structure

All RKM files should have the `.rkm` file extension, though it is not strictly necessarily for encoding and decoding purposes.

All valid `.rkm` files look like this:

`[SIGNATURE][FIELDS][CHECKSUM]`

Or, to elaborate further:

`[SIGNATURE][FIELD_NAME][DATA_LENGTH][DATA][FIELD_NAME][DATA_LENGTH][DATA]...[CHECKSUM]`

More details are given about these specific objects further down.

Unknown fields are skipped.

All strings are encoded and decoded to UTF-8.

### Signature

The RKM file signature is 8 bytes long.

Hex signature: `83 52 4B 4D 0D 0A 1A 0A`

Each file must start with the signature before writing any fields.

### Fields

Fields all look like this:
`[FIELD_NAME][DATA_LENGTH][DATA]`

**Field Name**

A field name must be exactly **4 bytes** long, and must not clash with other field names.

**Data Length**

The data length parameter must be exactly **4 bytes** long, and defines how many bytes long the data for the field is.

**Data**

The raw data, in bytes, for the given field must be exactly **as many bytes as specified in the data length parameter**.

### Checksum

The checksum is just like any other field, but it is special in several ways.
* The checksum operates over everything in the file, including the signature, all the way up until the checksum is written.
* The checksum is a SHA-512 checksum.
* The checksum acts as an end-of-file (EOF) marker.

A file without this checksum is not a valid RKM file.

## List of Fields

### `MCNM`

Map code name.
* Must be compatible with all relevant file systems.
* Must conform to this Regex: `^(?!-)[a-z\\d-]+[^-]$`

### `MDNM`

Map display name. The name as displayed to end-users. Does not have any restrictions other than being non-blank.

### `MATN`

Map author name. Does not have any restrictions other than being non-blank.

### `VTCS`

List of territories on the map. Also represents the vertices of a graph.

Format: `VTCS[length][vertex-count][vertex1][vertex2]...[vertexN]`

Vertex format: `[territory-id-length][territory-id][number-of-nuclei][x1][y1][x2][y2]...[xN][yN]`

All lengths are in bytes. Vertex count represents an integer.
Territory ID is a string. Nuclei are the nucleation sites for a bucket fill. You may also call them seed points. It's where a bucket fill is started. Each territory can have multiple of these, which is why you must list the number of nuclei. Like vertex count, the number of nuclei represents an integer.

### `EDGS`

List of territory pairs that are meant to be bordering each other on the map. Also represents the edges, or connections between two vertices, of a graph.

Format: `EDGS[length][edge-count][edge1][edge2]...[edgeN]`

Edge format: `[source-id-length][source-id][target-id-length][target-id]`

All lengths are in bytes. Edge count represents an integer.
Source ID and target ID are strings, and are the territory IDs of the source and target territories.

### `MIBS`

PNG Image of the base layer of the map.

Format: `MIBS[length][image-encoded-as-png]`

Length is in bytes, and the image is encoded to bytes.

### `MITX`

PNG image of the text layer of the map.

Format: `MITX[length][image-encoded-as-png]`

Length is in bytes, and the image is encoded to bytes.

### `CKSM`

SHA-512 checksum of the entire contents of the file. Must be placed at the end of the file.

## Contribute

Join our Discord server: [Riskrieg Discord](https://discord.gg/weU8jYDbW4)

## License

The code in this repository is licensed under the MIT license.
