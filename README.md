# Frame-Stats
## Sample configuration
```yaml
locations:
  left-lower-corner:
    ==: org.bukkit.Location
    world: world
    x: -304
    y: 76
    z: 164
    pitch: 0.0
    yaw: 0.0
  right-upper-corner:
    ==: org.bukkit.Location
    world: world
    x: -304
    y: 78
    z: 160
    pitch: 0.0
    yaw: 0.0
columns:
- ==: de.paul2708.framestats.configuration.ColumnConfiguration
  max-width: 30
  name: '#'
  index: 0
- ==: de.paul2708.framestats.configuration.ColumnConfiguration
  max-width: 128
  name: 'Spieler'
  index: 1
- ==: de.paul2708.framestats.configuration.ColumnConfiguration
  max-width: 64
  name: 'Kills'
  index: 2
- ==: de.paul2708.framestats.configuration.ColumnConfiguration
  max-width: 64
  name: 'Tode'
  index: 3
- ==: de.paul2708.framestats.configuration.ColumnConfiguration
  max-width: 64
  name: 'Punkte'
  index: 4
sizes:
  row-height: 20
rows: 10
```
