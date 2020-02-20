# Frame-Stats
## Sample configuration
```yaml
locations:
  left-lower-corner:
    ==: org.bukkit.Location
    world: world
    x: 100.0
    y: 100.0
    z: 100.0
    pitch: 0.0
    yaw: 0.0
  right-upper-corner:
    ==: org.bukkit.Location
    world: world
    x: 100.0
    y: 100.0
    z: 100.0
    pitch: 0.0
    yaw: 0.0
columns:
- ==: de.paul2708.framestats.configuration.ColumnConfiguration
  max-width: 128
  name: name
  index: 0
- ==: de.paul2708.framestats.configuration.ColumnConfiguration
  max-width: 128
  name: name
  index: 0
- ==: de.paul2708.framestats.configuration.ColumnConfiguration
  max-width: 128
  name: name
  index: 0
```
